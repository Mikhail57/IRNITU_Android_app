package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.ScheduleDay

interface ScheduleView : MvpView {
    fun showSelectGroup()
    fun showSchedule(group: String)
}
