package arsensaliev.io.gitbook.mvp.model

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser

class GithubUsersRepo {
    private val users = listOf<GithubUser>(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
    )

    fun getUsers(): List<GithubUser> {
        return users
    }
}