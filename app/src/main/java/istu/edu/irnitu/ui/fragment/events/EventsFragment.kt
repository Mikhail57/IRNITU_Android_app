package istu.edu.irnitu.ui.fragment.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.events.EventsView
import istu.edu.irnitu.presentation.presenter.events.EventsPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.entity.Event
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : MvpAppCompatFragment(), EventsView {
    override fun showLoading(isLoading: Boolean) {
        Toast.makeText(activity, "Loading: $isLoading", Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingError(msg: String) {
        Toast.makeText(activity, "Loading error: $msg", Toast.LENGTH_SHORT).show()
    }

    override fun showEvents(events: List<Event>) {
        textView.text = events.toString()
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

    init {
        Log.i(TAG, "INIT")
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
