package arsensaliev.io.gitbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import arsensaliev.io.gitbook.databinding.FragmentUsersBinding
import arsensaliev.io.gitbook.mvp.model.GithubUsersRepo
import arsensaliev.io.gitbook.mvp.presenter.UsersPresenter
import arsensaliev.io.gitbook.mvp.view.UsersView
import arsensaliev.io.gitbook.ui.App
import arsensaliev.io.gitbook.ui.BackClickListener
import arsensaliev.io.gitbook.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router)
    }

    private var ui: FragmentUsersBinding? = null

    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also { ui = it }.root

    override fun onDestroy() {
        super.onDestroy()
        ui = null
    }

    override fun init() {
        ui?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        ui?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backClick()
}