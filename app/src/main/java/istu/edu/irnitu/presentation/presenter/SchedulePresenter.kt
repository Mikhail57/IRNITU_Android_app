package istu.edu.irnitu.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application
import istu.edu.irnitu.entity.Faculty
import istu.edu.irnitu.model.repository.PreferencesProvider
import istu.edu.irnitu.model.repository.ScheduleRepository
import istu.edu.irnitu.presentation.view.ScheduleView
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class SchedulePresenter : MvpPresenter<ScheduleView>() {

    companion object {
        const val TAG = "SchedulePresenter"
    }

    @Inject
    lateinit var preferences: PreferencesProvider

    @Inject
    @Named("network")
    lateinit var scheduleNetworkRepository: ScheduleRepository

    private val disposable = CompositeDisposable()

    private var groups: List<Faculty>? = null

    init {
        Application.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        val group = preferences.get("selectedGroup") ?: return viewState.showSelectGroupButton()
//        val group = "ЭВМБ-18-1"
        viewState.showSchedule(group)
    }

    private fun getDay(calendarDay: Int) = if (calendarDay > 1) calendarDay - 1 else 7

    override fun onDestroy() {
        disposable.dispose()
    }

    fun loadGroupsFromNetwork() {
        viewState.showLoading("Загрузка списка факультетов...")
        disposable.add(scheduleNetworkRepository.getGroups().subscribe({
            this.groups = it
            val faculties = it.map(Faculty::title)
            viewState.showSelectFacultyList(faculties)
        }, {}))
    }

}
