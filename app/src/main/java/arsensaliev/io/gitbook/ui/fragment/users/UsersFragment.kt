package arsensaliev.io.gitbook.ui.fragment.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import arsensaliev.io.gitbook.databinding.FragmentUsersBinding
import arsensaliev.io.gitbook.mvp.presenter.users.UsersPresenter
import arsensaliev.io.gitbook.mvp.view.users.UsersView
import arsensaliev.io.gitbook.ui.App
import arsensaliev.io.gitbook.ui.BackButtonListener
import arsensaliev.io.gitbook.ui.adapter.users.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
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
        adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        ui?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backClick()
}