package arsensaliev.io.gitbook.mvp.navigation

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser): Screen
}