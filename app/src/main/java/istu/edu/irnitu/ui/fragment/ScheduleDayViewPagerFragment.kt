package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.ScheduleDayViewPagerView
import istu.edu.irnitu.presentation.presenter.ScheduleDayViewPagerPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.entity.Class
import istu.edu.irnitu.ui.adapters.ScheduleAdapter
import kotlinx.android.synthetic.main.fragment_schedule_day_view_pager.*

class ScheduleDayViewPagerFragment : MvpAppCompatFragment(), ScheduleDayViewPagerView {
    companion object {
        const val TAG = "ScheduleDayViewPagerFragment"

        private const val ARG_DAY_PARAM = "day"
        private const val ARG_DAY_STRING_PARAM = "day-str"
        private const val ARG_GROUP_PARAM = "group"

        fun newInstance(day: Int, dayString: String, group: String) =
            ScheduleDayViewPagerFragment()
                .apply {
                    arguments = Bundle().apply {
                        putInt(ARG_DAY_PARAM, day)
                        putString(ARG_DAY_STRING_PARAM, dayString)
                        putString(ARG_GROUP_PARAM, group)
                    }
                }
    }

    @InjectPresenter
    lateinit var mScheduleDayViewPagerPresenter: ScheduleDayViewPagerPresenter

    private var day: Int? = null
    private var dayString: String? = null
    private var group: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_day_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            day = it.getInt(ARG_DAY_PARAM)
            dayString = it.getString(ARG_DAY_STRING_PARAM)
            group = it.getString(ARG_GROUP_PARAM)
        }
        mScheduleDayViewPagerPresenter.init(day!!, dayString!!, group!!)
    }

    override fun showSchedule(schedule: List<Class>, dayString: String?) {
        scheduleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ScheduleAdapter(schedule, dayString ?: "Unknown day...")
        }
    }
}
