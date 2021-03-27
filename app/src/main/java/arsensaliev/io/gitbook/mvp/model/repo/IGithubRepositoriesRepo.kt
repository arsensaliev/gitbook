package arsensaliev.io.gitbook.mvp.model.repo

import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}