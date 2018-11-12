package istu.edu.irnitu.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.ScheduleView
import istu.edu.irnitu.presentation.presenter.SchedulePresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_schedule.*
import istu.edu.irnitu.ui.adapters.SchedulePagerAdapter
import istu.edu.irnitu.ui.dialog.LoadingDialog
import istu.edu.irnitu.ui.dialog.SelectDialog

class ScheduleFragment : MvpAppCompatFragment(), ScheduleView {
    companion object {
        const val TAG = "ScheduleFragment"

        fun newInstance(): ScheduleFragment {
            val fragment = ScheduleFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

    @InjectPresenter
    lateinit var mSchedulePresenter: SchedulePresenter

    private lateinit var pagerAdapter: FragmentPagerAdapter

    private var loadingDialog: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun showSelectGroupButton() {
        scheduleNoGroup.visibility = View.VISIBLE
        scheduleViewPager.visibility = View.GONE

        scheduleNoGroup.buttonOnClickListener = View.OnClickListener {
            mSchedulePresenter.loadGroupsFromNetwork()
            val dialog = LoadingDialog.newInstance("Lol", "Kek")
            dialog.show(fragmentManager, "LOL")
        }
    }

    override fun showSchedule(group: String) {
        scheduleNoGroup.visibility = View.GONE
        scheduleViewPager.visibility = View.VISIBLE

        pagerAdapter = SchedulePagerAdapter(childFragmentManager, group)
        scheduleViewPager.adapter = pagerAdapter
    }

    override fun showLoading(reason: String) {
        loadingDialog = LoadingDialog.newInstance("Загрузка...", reason)
        loadingDialog?.show(fragmentManager, "loading")
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }

    override fun showSelectFacultyList(faculties: List<String>) {
        SelectDialog.newInstance(
            "Выберите факультет",
            faculties,
            DialogInterface.OnClickListener { _, which ->
                mSchedulePresenter.selectedFaculty(faculties[which])
            }).show(fragmentManager, "faculty")
    }

    override fun showSelectCourseList(courses: List<String>) {
        SelectDialog.newInstance(
            "Выберите курс",
            courses,
            DialogInterface.OnClickListener { _, which ->
                mSchedulePresenter.selectedCourse(courses[which])
            }).show(fragmentManager, "course")
    }

    override fun showSelectGroupList(groups: List<String>) {
        SelectDialog.newInstance(
            "Выберите группу",
            groups,
            DialogInterface.OnClickListener { _, which ->
                mSchedulePresenter.selectedGroup(groups[which])
            }).show(fragmentManager, "group")
    }

    override fun showError(reason: String) {
        Toast.makeText(context, reason, Toast.LENGTH_LONG).show()
    }
}
