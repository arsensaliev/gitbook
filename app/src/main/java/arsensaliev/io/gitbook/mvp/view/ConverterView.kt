package arsensaliev.io.gitbook.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ConverterView : MvpView {
    fun init()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun pickImage()

    fun showConvertInProgress()
    fun hideConvertInProgress()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showConvertSuccess()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showConvertCancel()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showConvertError()

}