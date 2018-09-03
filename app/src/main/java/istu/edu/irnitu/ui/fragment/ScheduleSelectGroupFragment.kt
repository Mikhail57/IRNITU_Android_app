package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.ScheduleSelectGroupView
import istu.edu.irnitu.presentation.presenter.ScheduleSelectGroupPresenter

import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class ScheduleSelectGroupFragment : MvpAppCompatFragment(), ScheduleSelectGroupView {
    override fun showGroups() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @InjectPresenter
    lateinit var mScheduleSelectGroupPresenter: ScheduleSelectGroupPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_select_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val TAG = "ScheduleSelectGroupFragment"

        fun newInstance(): ScheduleSelectGroupFragment {
            val fragment: ScheduleSelectGroupFragment = ScheduleSelectGroupFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
