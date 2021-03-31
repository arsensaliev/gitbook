package arsensaliev.io.gitbook.mvp.model.repo

import arsensaliev.io.gitbook.mvp.model.api.IDataSource
import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(val api: IDataSource) : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = user.reposUrl?.let { url ->
        api.getRepositories(url).subscribeOn(Schedulers.io())
    } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
        .subscribeOn(Schedulers.io())
}