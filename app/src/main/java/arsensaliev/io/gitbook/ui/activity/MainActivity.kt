package arsensaliev.io.gitbook.ui.activity

import android.os.Bundle
import arsensaliev.io.gitbook.R
import arsensaliev.io.gitbook.databinding.ActivityMainBinding
import arsensaliev.io.gitbook.mvp.presenter.main.MainPresenter
import arsensaliev.io.gitbook.mvp.view.main.MainView
import arsensaliev.io.gitbook.ui.App
import arsensaliev.io.gitbook.ui.BackButtonListener
import arsensaliev.io.gitbook.ui.adapter.users.UsersRVAdapter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = AppNavigator(this, R.id.container)

    private val ui: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        setContentView(ui.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}
