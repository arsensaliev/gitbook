package arsensaliev.io.gitbook.mvp.presenter

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(val router: Router, val user: GithubUser) : MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUserName(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}