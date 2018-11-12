package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.ScheduleDay

interface ScheduleView : MvpView {
    fun showSelectGroupButton()
    fun showSchedule(group: String)
    fun showLoading(reason: String)
    fun hideLoading()
    fun showSelectFacultyList(faculties: List<String>)
    fun showSelectCourseList(courses: List<String>)
    fun showSelectGroupList(groups: List<String>)
}
