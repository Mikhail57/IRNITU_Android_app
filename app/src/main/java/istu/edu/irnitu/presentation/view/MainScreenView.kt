package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.Tabs

interface MainScreenView : MvpView {
    fun goToTab(tab: Tabs)
}