package arsensaliev.io.gitbook.mvp.model.navigation

import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser): Screen
//    fun repository(repository: GithubRepository): Screen
}