package arsensaliev.io.gitbook.mvp.model.repo

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo {
    private val users = listOf<GithubUser>(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
    )


    fun getUsers() = Observable.fromCallable { users }
        .subscribeOn(Schedulers.io())
}