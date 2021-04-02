package arsensaliev.io.gitbook.di.module.image

import android.widget.ImageView
import arsensaliev.io.gitbook.mvp.model.image.IImageLoader
import arsensaliev.io.gitbook.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}