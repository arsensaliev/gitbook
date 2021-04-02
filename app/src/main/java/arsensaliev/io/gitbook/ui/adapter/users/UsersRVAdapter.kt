package arsensaliev.io.gitbook.ui.adapter.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import arsensaliev.io.gitbook.databinding.ItemUsersBinding
import arsensaliev.io.gitbook.mvp.model.image.IImageLoader
import arsensaliev.io.gitbook.mvp.presenter.users.IUsersListPresenter
import arsensaliev.io.gitbook.mvp.view.users.list.IUserItemView
import javax.inject.Inject

class UsersRVAdapter(val presenter: IUsersListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(val ui: ItemUsersBinding) : RecyclerView.ViewHolder(ui.root),
        IUserItemView {

        override var pos = -1

        override fun setLogin(text: String) = with(ui) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) = with(ui) {
            imageLoader.load(url, ivAvatar)
        }
    }

}