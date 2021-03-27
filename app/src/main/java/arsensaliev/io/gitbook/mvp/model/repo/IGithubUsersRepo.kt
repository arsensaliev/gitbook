package arsensaliev.io.gitbook.mvp.model.repo

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers() : Single<List<GithubUser>>
}