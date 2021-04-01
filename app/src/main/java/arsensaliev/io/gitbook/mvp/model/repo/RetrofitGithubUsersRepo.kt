package arsensaliev.io.gitbook.mvp.model.repo

import arsensaliev.io.gitbook.mvp.model.api.IDataSource
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.model.entity.room.RoomGithubUser
import arsensaliev.io.gitbook.mvp.model.entity.room.db.Database
import arsensaliev.io.gitbook.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUsers = users.map { user ->
                        RoomGithubUser(
                            id = user.id,
                            login = user.login,
                            avatarUrl = user.avatarUrl,
                            reposUrl = user.reposUrl
                        )
                    }
                    db.userDao.insert(roomUsers)
                    users
                }
            }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(
                        id = roomUser.id,
                        login = roomUser.login,
                        avatarUrl = roomUser.avatarUrl,
                        reposUrl = roomUser.reposUrl
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}