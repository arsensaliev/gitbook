package arsensaliev.io.gitbook.ui.navigation

import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.model.navigation.IScreens
import arsensaliev.io.gitbook.ui.fragment.user.UserFragment
import arsensaliev.io.gitbook.ui.fragment.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(githubUser: GithubUser): Screen =
        FragmentScreen { UserFragment.newInstance(githubUser) }

//    override fun repository(repository: GithubRepository): Screen = FragmentScreen {UsersFragment.newInstance()}
}