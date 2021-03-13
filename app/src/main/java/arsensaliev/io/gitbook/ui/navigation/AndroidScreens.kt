package arsensaliev.io.gitbook.ui.navigation

import arsensaliev.io.gitbook.mvp.navigation.IScreens
import arsensaliev.io.gitbook.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}