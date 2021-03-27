package arsensaliev.io.gitbook.mvp.presenter

import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.model.navigation.IScreens
import arsensaliev.io.gitbook.mvp.model.repo.IGithubUsersRepo
import arsensaliev.io.gitbook.mvp.presenter.list.IUsersListPresenter
import arsensaliev.io.gitbook.mvp.view.UsersView
import arsensaliev.io.gitbook.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepo: IGithubUsersRepo,
    val router: Router,
    val screens: IScreens,
    val uiScheduler: Scheduler
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount(): Int = users.size
    }

    val compositeDisposable = CompositeDisposable()

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }

    private fun loadData() {
        val disposable: Disposable = usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                it.printStackTrace()
            })

        compositeDisposable.addAll(disposable)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
