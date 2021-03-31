package arsensaliev.io.gitbook.mvp.view.user

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun setUserName(name: String)
    fun loadAvatar(url: String)
    fun init()
    fun updateList()
}