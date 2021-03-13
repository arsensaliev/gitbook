package arsensaliev.io.gitbook.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView : MvpView {
    fun init()
    fun updateList()
}