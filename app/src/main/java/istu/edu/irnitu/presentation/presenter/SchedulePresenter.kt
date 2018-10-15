package istu.edu.irnitu.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import istu.edu.irnitu.Application
import istu.edu.irnitu.model.repository.PreferencesRepository
import istu.edu.irnitu.presentation.view.ScheduleView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SchedulePresenter : MvpPresenter<ScheduleView>() {

    @Inject
    lateinit var preferences: PreferencesRepository

//    @Inject
//    lateinit var router: Router

    init {
        Application.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        val group = preferences.get("selectedGroup") ?: return viewState.goToSelectGroupScreen()
        viewState.showSchedule()
    }
}
