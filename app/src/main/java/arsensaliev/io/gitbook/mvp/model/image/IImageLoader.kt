package arsensaliev.io.gitbook.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}