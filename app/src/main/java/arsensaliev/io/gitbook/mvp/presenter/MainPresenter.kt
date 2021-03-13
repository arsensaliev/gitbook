package arsensaliev.io.gitbook.mvp.presenter

import arsensaliev.io.gitbook.mvp.model.GithubUsersRepo
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.presenter.list.IUsersListPresenter
import arsensaliev.io.gitbook.mvp.view.MainView
import arsensaliev.io.gitbook.mvp.view.list.IUserItemView
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<MainView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount(): Int = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}
