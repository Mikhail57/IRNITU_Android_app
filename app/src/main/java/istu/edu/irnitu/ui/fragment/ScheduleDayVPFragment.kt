package istu.edu.irnitu.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application

import istu.edu.irnitu.R
import istu.edu.irnitu.model.repository.ScheduleRepository
import istu.edu.irnitu.ui.adapters.ScheduleAdapter
import kotlinx.android.synthetic.main.fragment_schedule_day.*
import java.util.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_DAY_PARAM = "day"
private const val ARG_DAY_STRING_PARAM = "day-str"
private const val ARG_GROUP_PARAM = "group"

/**
 * A simple [Fragment] to be used in Schedule ViewPager.
 *
 * Use the [ScheduleDayVPFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleDayVPFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var day: Int? = null
    private var dayString: String? = null
    private var group: String? = null

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var scheduleRepository: ScheduleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getInt(ARG_DAY_PARAM)
            dayString = it.getString(ARG_DAY_STRING_PARAM)
            group = it.getString(ARG_GROUP_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Application.appComponent.inject(this)

        disposable.add(scheduleRepository.getGroupScheduleForDay(group!!, day!!).subscribe({
            scheduleRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ScheduleAdapter(it, dayString ?: "Unknown day...")
            }
        }, {}))
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_day, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        disposable.dispose()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param day Day for schedule.
         * @return A new instance of fragment ScheduleDayVPFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(group: String, day: Int, dayString: String) =
            ScheduleDayVPFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DAY_PARAM, day)
                    putString(ARG_DAY_STRING_PARAM, dayString)
                    putString(ARG_GROUP_PARAM, group)
                }
            }
    }
}
