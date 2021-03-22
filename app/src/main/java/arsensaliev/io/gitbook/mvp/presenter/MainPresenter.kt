package arsensaliev.io.gitbook.mvp.presenter

import arsensaliev.io.gitbook.mvp.navigation.IScreens
import arsensaliev.io.gitbook.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.converter())
    }

    fun backClicked() {
        router.exit()
    }
}
