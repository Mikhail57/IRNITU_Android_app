package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SettingsView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showAreYouSureDialog()
}
