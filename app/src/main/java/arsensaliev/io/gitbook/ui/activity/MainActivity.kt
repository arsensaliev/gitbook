package arsensaliev.io.gitbook.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import arsensaliev.io.gitbook.databinding.ActivityMainBinding
import arsensaliev.io.gitbook.mvp.model.GithubUsersRepo
import arsensaliev.io.gitbook.mvp.presenter.MainPresenter
import arsensaliev.io.gitbook.mvp.view.MainView
import arsensaliev.io.gitbook.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val ui: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val presenter by moxyPresenter {
        MainPresenter(GithubUsersRepo())
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)
    }

    override fun init() {
        ui.rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        ui.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

}
