package istu.edu.irnitu.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import istu.edu.irnitu.Application
import istu.edu.irnitu.TabScreens
import istu.edu.irnitu.Tabs
import istu.edu.irnitu.presentation.view.MainScreenView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainScreenPresenter : MvpPresenter<MainScreenView>() {


    init {
    }

    fun onBackPressed() {
    }
}