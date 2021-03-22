package arsensaliev.io.gitbook.mvp.model.converter

import io.reactivex.rxjava3.core.Completable

interface IConverter {
    fun convert(image: Image): Completable
}