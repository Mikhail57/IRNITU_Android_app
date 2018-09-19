package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.ScheduleSelectFacultyView
import istu.edu.irnitu.presentation.presenter.ScheduleSelectFacultyPresenter

import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class ScheduleSelectFacultyFragment : MvpAppCompatFragment(), ScheduleSelectFacultyView {

    @InjectPresenter
    lateinit var mScheduleSelectFacultyPresenter: ScheduleSelectFacultyPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_select_faculty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showLoading(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val TAG = "ScheduleSelectFacultyFragment"

        fun newInstance(): ScheduleSelectFacultyFragment {
            val fragment: ScheduleSelectFacultyFragment = ScheduleSelectFacultyFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
