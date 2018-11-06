package istu.edu.irnitu.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application
import istu.edu.irnitu.entity.ScheduleDay
import istu.edu.irnitu.model.repository.PreferencesRepository
import istu.edu.irnitu.model.repository.ScheduleRepository
import istu.edu.irnitu.presentation.view.ScheduleView
import java.util.*
import javax.inject.Inject

@InjectViewState
class SchedulePresenter : MvpPresenter<ScheduleView>() {

    @Inject
    lateinit var preferences: PreferencesRepository

    @Inject
    lateinit var scheduleRepository: ScheduleRepository

    private val disposable = CompositeDisposable()

    init {
        Application.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
//        val group = preferences.get("selectedGroup") ?: return viewState.showSelectGroup()
        val group = "ЭВМБ-18-1"

        val calendar = GregorianCalendar(Locale.CANADA)
        val week = calendar.get(Calendar.WEEK_OF_YEAR) % 2
        val day = getDay(calendar.get(Calendar.DAY_OF_WEEK)) + week * 7
        Log.i(this.javaClass.canonicalName, "Day $day")

        disposable.add(scheduleRepository.getGroupScheduleForDay(group, day).subscribe({
            val schedule = ScheduleDay("$week $day", it)
            viewState.showSchedule(schedule)
            Log.i(this.javaClass.canonicalName, it.toString())
            Log.i(this.javaClass.canonicalName, "Size: ${it.size}")
        }, {

        }))

    }

    private fun getDay(calendarDay: Int) = if (calendarDay > 1) calendarDay - 1 else 7

    override fun onDestroy() {
        disposable.dispose()
    }
}
