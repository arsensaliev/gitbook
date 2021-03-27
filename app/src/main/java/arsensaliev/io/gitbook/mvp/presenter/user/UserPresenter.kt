package arsensaliev.io.gitbook.mvp.presenter.user

import arsensaliev.io.gitbook.mvp.model.entity.GithubRepository
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.model.navigation.IScreens
import arsensaliev.io.gitbook.mvp.model.repo.IGithubRepositoriesRepo
import arsensaliev.io.gitbook.mvp.view.user.UserView
import arsensaliev.io.gitbook.mvp.view.user.list.IRepoItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserPresenter(
    val router: Router,
    val user: GithubUser,
    val repositoriesRepo: IGithubRepositoriesRepo,
    val screens: IScreens,
    val uiSchedulers: Scheduler
) : MvpPresenter<UserView>() {

    class RepositoriesListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        override fun bindView(view: IRepoItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }

        override fun getCount(): Int = repositories.size
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        user.login?.let { viewState.setUserName(it) }

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
//            router.navigateTo(screens.repository(repository))
        }
    }

    private fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(uiSchedulers)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}