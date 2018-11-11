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
import istu.edu.irnitu.ui.activity.SelectGroupActivity
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
        scheduleNoGroup.visibility = View.VISIBLE
        scheduleViewPager.visibility = View.GONE

        scheduleNoGroup.buttonOnClickListener = View.OnClickListener {
            startActivity(SelectGroupActivity.getIntent(context!!))
        }
    }

    override fun showSchedule(group: String) {
        scheduleNoGroup.visibility = View.GONE
        scheduleViewPager.visibility = View.VISIBLE

        pagerAdapter = SchedulePagerAdapter(childFragmentManager, group)
        scheduleViewPager.adapter = pagerAdapter
    }
}
