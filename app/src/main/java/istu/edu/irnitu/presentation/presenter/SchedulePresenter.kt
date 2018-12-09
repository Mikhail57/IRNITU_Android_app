package istu.edu.irnitu.presentation.presenter

import android.util.Log
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

    @field:[Inject Named("network")]
    lateinit var scheduleNetworkRepository: ScheduleRepository

    @field:[Inject Named("db")]
    lateinit var scheduleDbRepository: ScheduleRepository

    private val disposable = CompositeDisposable()

    private var groups: List<Faculty>? = null
    private var faculty: String? = null

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
            viewState.hideLoading()
            this.groups = it
            val faculties = it.map(Faculty::title)
            viewState.showSelectFacultyList(faculties)
        }, {
            viewState.hideLoading()
            viewState.showError("Произошла ошибка: ${it.localizedMessage}")
        }))
    }

    fun selectedFaculty(faculty: String) {
        this.faculty = faculty
        val courses = groups?.find { it.title == faculty }?.courses?.map { "${it.key} курс" }
        courses?.apply {
            viewState.showSelectCourseList(this)
        } ?: viewState.showError("Что-то пошло не так. Попробуйте попозже")
    }

    fun selectedCourse(course: String) {
        val courseNumber = course.split(' ')[0].toInt()
        val groups = groups?.find { it.title == faculty }?.courses?.get(courseNumber)
        groups?.apply {
            viewState.showSelectGroupList(this)
        } ?: viewState.showError("Что-то пошло не так. Попробуйте попозже")
    }

    fun selectedGroup(group: String) {
        viewState.showLoading("Скачивание расписания для группы $group...")
        disposable.add(scheduleNetworkRepository.getGroupSchedule(group).subscribe({
            Log.d(TAG, "Loaded schedule for group $group is $it")
            disposable.add(scheduleDbRepository.insertSchedule(it).subscribe {
                viewState.hideLoading()
                preferences.set("selectedGroup", group)
                viewState.showSchedule(group)
            })
        }, {}))
    }

    fun onDismissDialog() {
        viewState.hideDialog()
    }

}
