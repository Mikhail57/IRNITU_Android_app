package istu.edu.irnitu.ui.fragment.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.events.EventsView
import istu.edu.irnitu.presentation.presenter.events.EventsPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.entity.Event

class EventsFragment : MvpAppCompatFragment(), EventsView {
    override fun showLoading(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEvents(events: List<Event>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val TAG = "EventsFragment"

        fun newInstance(): EventsFragment {
            val fragment: EventsFragment = EventsFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mEventsPresenter: EventsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
