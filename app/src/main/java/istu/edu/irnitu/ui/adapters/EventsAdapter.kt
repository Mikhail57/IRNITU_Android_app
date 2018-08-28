package istu.edu.irnitu.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import istu.edu.irnitu.Application
import istu.edu.irnitu.R
import istu.edu.irnitu.entity.Event
import kotlinx.android.synthetic.main.item_event.view.*
import javax.inject.Inject

class EventsAdapter(var events: List<Event>) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    @Inject
    lateinit var context: Context

    init {
        Application.appComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventsViewHolder(view)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]
        holder.apply {
            title.text = event.name
            date.text = DateUtils.formatDateTime(context, event.startsAt.time, DateUtils.FORMAT_ABBREV_RELATIVE)
            place.text = "LOL"
            image.setImageURI(event.image.defaultUrl)
        }
    }

    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: SimpleDraweeView = itemView.findViewById(R.id.eventImage)
        val title: TextView = itemView.findViewById(R.id.eventTitle)
        val date: TextView = itemView.findViewById(R.id.eventDate)
        val place: TextView = itemView.findViewById(R.id.eventAddress)
    }
}