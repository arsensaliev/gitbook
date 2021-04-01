package arsensaliev.io.gitbook.ui.fragment.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import arsensaliev.io.gitbook.databinding.FragmentUsersBinding
import arsensaliev.io.gitbook.mvp.model.api.ApiHolder
import arsensaliev.io.gitbook.mvp.model.entity.room.db.Database
import arsensaliev.io.gitbook.mvp.model.repo.RetrofitGithubUsersRepo
import arsensaliev.io.gitbook.mvp.presenter.users.UsersPresenter
import arsensaliev.io.gitbook.mvp.view.users.UsersView
import arsensaliev.io.gitbook.ui.App
import arsensaliev.io.gitbook.ui.BackButtonListener
import arsensaliev.io.gitbook.ui.adapter.users.UsersRVAdapter
import arsensaliev.io.gitbook.ui.image.GlideImageLoader
import arsensaliev.io.gitbook.ui.navigation.AndroidScreens
import arsensaliev.io.gitbook.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            RetrofitGithubUsersRepo(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                Database.getInstance()
            ),
            App.instance.router,
            AndroidScreens(),
            AndroidSchedulers.mainThread()
        )
    }

    private var ui: FragmentUsersBinding? = null

    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also { ui = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun init() {
        ui?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        ui?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backClick()
}