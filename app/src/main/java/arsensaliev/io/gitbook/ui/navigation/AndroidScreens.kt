package arsensaliev.io.gitbook.ui.navigation

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.navigation.IScreens
import arsensaliev.io.gitbook.ui.fragment.UserFragment
import arsensaliev.io.gitbook.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(githubUser: GithubUser): Screen =
        FragmentScreen { UserFragment.newInstance(githubUser) }
}