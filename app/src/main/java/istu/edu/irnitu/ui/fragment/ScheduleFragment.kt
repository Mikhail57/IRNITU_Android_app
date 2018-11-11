package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.ScheduleView
import istu.edu.irnitu.presentation.presenter.SchedulePresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_schedule.*
import istu.edu.irnitu.ui.adapters.SchedulePagerAdapter

class ScheduleFragment : MvpAppCompatFragment(), ScheduleView {
    companion object {
        const val TAG = "ScheduleFragment"

        fun newInstance(): ScheduleFragment {
            val fragment: ScheduleFragment = ScheduleFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mSchedulePresenter: SchedulePresenter

    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun showSelectGroup() {

    }

    override fun showSchedule(group: String) {
        pagerAdapter = SchedulePagerAdapter(childFragmentManager, group)
        scheduleViewPager.adapter = pagerAdapter
    }
}
