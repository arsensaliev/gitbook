package arsensaliev.io.gitbook.ui.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import arsensaliev.io.gitbook.databinding.FragmentUserBinding
import arsensaliev.io.gitbook.mvp.model.entity.GithubUser
import arsensaliev.io.gitbook.mvp.presenter.user.UserPresenter
import arsensaliev.io.gitbook.mvp.view.user.UserView
import arsensaliev.io.gitbook.ui.App
import arsensaliev.io.gitbook.ui.BackButtonListener
import arsensaliev.io.gitbook.ui.adapter.user.UserRVAdapter
import arsensaliev.io.gitbook.ui.image.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            user = user,
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var ui: FragmentUserBinding? = null
    private var adapter: UserRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUserBinding.inflate(inflater, container, false).also { ui = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun setUserName(name: String) {
        ui?.userName?.text = name
    }

    override fun loadAvatar(url: String) {
        ui?.userAvatar?.let { GlideImageLoader().load(url, it) }
    }

    override fun init() {
        ui?.recyclerUserRepo?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserRVAdapter(presenter.repositoriesListPresenter)
        ui?.recyclerUserRepo?.isNestedScrollingEnabled = false
        ui?.recyclerUserRepo?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}