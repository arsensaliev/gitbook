package arsensaliev.io.gitbook.mvp.model.repo

import arsensaliev.io.gitbook.mvp.model.api.IDataSource
import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.model.entity.room.RoomGithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.room.db.Database
import arsensaliev.io.gitbook.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = db.userDao.findByLogin(user.login)
                                    ?: throw  RuntimeException("No user in DB")
                                val roomRepos = repositories.map { repo ->
                                    RoomGithubRepository(
                                        id = repo.id,
                                        name = repo.name,
                                        forksCount = repo.forksCount,
                                        userId = roomUser.id
                                    )
                                }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.findByLogin(user.login)
                        ?: throw  RuntimeException("No user in DB")
                    db.repositoryDao.findForUser(roomUser.id).map { roomRepo ->
                        GithubRepository(
                            id = roomRepo.id,
                            name = roomRepo.name,
                            forksCount = roomRepo.forksCount
                        )
                    }
                }
            }

        }.subscribeOn(Schedulers.io())
}