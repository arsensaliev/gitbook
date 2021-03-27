package arsensaliev.io.gitbook.ui.image

import android.widget.ImageView
import arsensaliev.io.gitbook.mvp.model.image.IImageLoader
import com.bumptech.glide.Glide

class GlideImageLoader : IImageLoader<ImageView> {

    override fun load(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }

}