package istu.edu.irnitu.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application
import istu.edu.irnitu.entity.ScheduleDay
import istu.edu.irnitu.model.repository.PreferencesProvider
import istu.edu.irnitu.model.repository.ScheduleRepository
import istu.edu.irnitu.presentation.view.ScheduleView
import android.text.format.DateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class SchedulePresenter : MvpPresenter<ScheduleView>() {

    companion object {
        const val TAG = "SchedulePresenter"
    }

    @Inject
    lateinit var preferences: PreferencesProvider

    @Inject
    lateinit var scheduleRepository: ScheduleRepository

    private val disposable = CompositeDisposable()

    init {
        Application.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        val group = preferences.get("selectedGroup") ?: return viewState.showSelectGroup()
//        val group = "ЭВМБ-18-1"
        val calendar = GregorianCalendar()
        val week = calendar.get(Calendar.WEEK_OF_YEAR) % 2
        val day = getDay(calendar.get(Calendar.DAY_OF_WEEK)) + week * 7
        Log.d(TAG, "Day $day")
        viewState.showSchedule(group)
    }

    private fun getDay(calendarDay: Int) = if (calendarDay > 1) calendarDay - 1 else 7

    override fun onDestroy() {
        disposable.dispose()
    }
}
