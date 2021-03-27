package arsensaliev.io.gitbook.mvp.presenter.main

import arsensaliev.io.gitbook.mvp.model.navigation.IScreens
import arsensaliev.io.gitbook.mvp.view.main.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
