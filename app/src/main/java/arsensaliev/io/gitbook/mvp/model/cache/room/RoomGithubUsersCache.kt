package arsensaliev.io.gitbook.mvp.model.cache.room

import arsensaliev.io.gitbook.mvp.model.cache.IGithubUsersCache
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.model.entity.room.RoomGithubUser
import arsensaliev.io.gitbook.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(val db: Database) : IGithubUsersCache {
    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                id = roomUser.id,
                login = roomUser.login,
                avatarUrl = roomUser.avatarUrl,
                reposUrl = roomUser.reposUrl
            )
        }
    }

    override fun putUsers(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                id = user.id,
                login = user.login,
                avatarUrl = user.avatarUrl ?: "",
                reposUrl = user.reposUrl ?: ""
            )
        }

        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

}