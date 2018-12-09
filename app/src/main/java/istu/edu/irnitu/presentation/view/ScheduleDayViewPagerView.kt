package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.Class

interface ScheduleDayViewPagerView : MvpView {
    fun showSchedule(schedule: List<Class>, dayString: String?)
}
