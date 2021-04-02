package arsensaliev.io.gitbook.mvp.model.cache.room

import arsensaliev.io.gitbook.mvp.model.cache.IGithubRepositoriesCache
import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.model.entity.room.RoomGithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(val db: Database) : IGithubRepositoriesCache {
    override fun getUserRepos(user: GithubUser) = Single.fromCallable {
        val roomUser =
            db.userDao.findByLogin(user.login) ?: throw RuntimeException("No such user in cache")

        return@fromCallable db.repositoryDao.findForUser(roomUser.id).map {
            GithubRepository(
                id = it.id,
                name = it.name,
                forksCount = it.forksCount
            )
        }
    }

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>) =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
                ?: throw RuntimeException("No such user in cache")

            val roomRepos = repositories.map {
                RoomGithubRepository(
                    id = it.id,
                    name = it.name,
                    forksCount = it.forksCount ?: 0,
                    userId = roomUser.id
                )
            }

            db.repositoryDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())


}