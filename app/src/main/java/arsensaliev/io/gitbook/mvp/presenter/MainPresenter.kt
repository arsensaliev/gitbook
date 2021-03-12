package arsensaliev.io.gitbook.mvp.presenter

import arsensaliev.io.gitbook.R
import arsensaliev.io.gitbook.mvp.model.CountersModel
import arsensaliev.io.gitbook.mvp.view.MainView

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterOneClick() {
        val nextValue = model.next(0)
        view.setButtonOneText(nextValue.toString())
    }

    fun counterTwoClick() {
        val nextValue = model.next(1)
        view.setButtonOneText(nextValue.toString())
    }

    fun counterThreeClick() {
        val nextValue = model.next(2)
        view.setButtonOneText(nextValue.toString())
    }
}
