package arsensaliev.io.gitbook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import arsensaliev.io.gitbook.databinding.ActivityMainBinding
import arsensaliev.io.gitbook.mvp.presenter.MainPresenter
import arsensaliev.io.gitbook.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val ui: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        ui.btnCounter1.setOnClickListener(listener)
        ui.btnCounter2.setOnClickListener(listener)
        ui.btnCounter3.setOnClickListener(listener)
    }

    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(index: Int, text: String) {
        when(index){
            0 -> ui.btnCounter1.text = text
            1 -> ui.btnCounter2.text = text
            2 -> ui.btnCounter3.text = text
        }
    }
}
