package istu.edu.irnitu.presentation.presenter

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application
import istu.edu.irnitu.model.repository.ScheduleRepository
import istu.edu.irnitu.presentation.view.ScheduleDayViewPagerView
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class ScheduleDayViewPagerPresenter : MvpPresenter<ScheduleDayViewPagerView>() {
    @field:[Inject Named("db")]
    lateinit var scheduleRepository: ScheduleRepository

    private val disposable = CompositeDisposable()

    init {
        Application.appComponent.inject(this)
    }


    fun init(day: Int, dayString: String, group: String) {
        disposable.add(scheduleRepository.getGroupScheduleForDay(group, day).subscribe({
            viewState.showSchedule(it, dayString)
        }, {
            Log.e("ScheduleDayVPPresenter", "Kek, how could this happen?")
        }))
    }

}
