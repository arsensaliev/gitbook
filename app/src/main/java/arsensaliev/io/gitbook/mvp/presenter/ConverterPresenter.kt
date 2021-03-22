package arsensaliev.io.gitbook.mvp.presenter

import android.util.Log
import arsensaliev.io.gitbook.mvp.model.converter.Image
import arsensaliev.io.gitbook.mvp.view.ConverterView
import arsensaliev.io.gitbook.ui.converter.AndroidConverter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ConverterPresenter(
    private val router: Router,
    private val uiScheduler: Scheduler,
    private val converter: AndroidConverter
) : MvpPresenter<ConverterView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun convertClick() {
        viewState.pickImage()
    }

    var conversionDisposable: Disposable? = null

    fun imageSelected(image: Image) {
        viewState.showConvertInProgress()
        conversionDisposable = converter.convert(image)
            .observeOn(uiScheduler)
            .subscribe({
                Log.d("CONVERTER", "Успешная конвертация")
                viewState.hideConvertInProgress()
//                viewState.showConvertSuccess()
            }, {
                Log.d("CONVERTER", "Ошибка")
                viewState.hideConvertInProgress()
//                viewState.showConvertError()
            })
    }

    fun convertCancel() {
        conversionDisposable?.dispose()
        viewState.hideConvertInProgress()
        viewState.showConvertCancel()
    }
}