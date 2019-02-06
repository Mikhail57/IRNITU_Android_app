package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.EventsView
import istu.edu.irnitu.presentation.presenter.EventsPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.entity.Event
import istu.edu.irnitu.ui.adapters.EventsAdapter
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : MvpAppCompatFragment(), EventsView {

    @InjectPresenter
    lateinit var mEventsPresenter: EventsPresenter

    private lateinit var viewAdapter: EventsAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    private var events: List<Event> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewAdapter = EventsAdapter(events)
        viewManager = androidx.recyclerview.widget.LinearLayoutManager(context)

        eventsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


    override fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            eventsLoadingScreen.visibility = View.VISIBLE
            eventsContentScreen.visibility = View.GONE
        } else {
            eventsLoadingScreen.visibility = View.GONE
            eventsContentScreen.visibility = View.VISIBLE
        }
    }

    override fun showLoadingError(msg: String) {
        Toast.makeText(activity, "Loading error: $msg", Toast.LENGTH_SHORT).show()
    }

    override fun showEvents(events: List<Event>) {
        this.events = events
        viewAdapter.events = events
        viewAdapter.notifyDataSetChanged()
    }

    companion object {
        const val TAG = "EventsFragment"

        fun newInstance(): EventsFragment {
            val fragment = EventsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
