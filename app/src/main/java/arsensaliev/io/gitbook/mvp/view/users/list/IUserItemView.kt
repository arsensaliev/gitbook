package arsensaliev.io.gitbook.mvp.view.users.list

interface IUserItemView : ItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}