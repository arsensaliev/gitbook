package arsensaliev.io.gitbook.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import arsensaliev.io.gitbook.databinding.ActivityMainBinding
import arsensaliev.io.gitbook.mvp.presenter.MainPresenter
import arsensaliev.io.gitbook.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val ui: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        ui.btnCounter1.setOnClickListener { presenter.counterOneClick() }
        ui.btnCounter2.setOnClickListener { presenter.counterTwoClick() }
        ui.btnCounter3.setOnClickListener { presenter.counterThreeClick() }
    }

    override fun setButtonOneText(text: String) {
        ui.btnCounter1.text = text
    }

    override fun setButtonTwoText(text: String) {
        ui.btnCounter2.text = text
    }

    override fun setButtonThreeText(text: String) {
        ui.btnCounter3.text = text
    }
}
