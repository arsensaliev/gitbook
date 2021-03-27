package arsensaliev.io.gitbook.mvp.presenter

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(val router: Router, val user: GithubUser) : MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user.login?.let { viewState.setUserName(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}