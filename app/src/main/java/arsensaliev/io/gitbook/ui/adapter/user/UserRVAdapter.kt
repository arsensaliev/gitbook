package arsensaliev.io.gitbook.ui.adapter.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import arsensaliev.io.gitbook.databinding.ItemRepoBinding
import arsensaliev.io.gitbook.mvp.presenter.user.IRepositoryListPresenter
import arsensaliev.io.gitbook.mvp.view.user.list.IRepoItemView

class UserRVAdapter(val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(val ui: ItemRepoBinding) : RecyclerView.ViewHolder(ui.root),
        IRepoItemView {
        override fun setName(name: String) = with(ui) {
            tvUserRepo.text = name
        }

        override var pos = -1
    }

}