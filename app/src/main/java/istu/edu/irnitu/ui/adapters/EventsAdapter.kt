package istu.edu.irnitu.ui.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import istu.edu.irnitu.Application
import istu.edu.irnitu.R
import istu.edu.irnitu.entity.Event
import java.text.SimpleDateFormat
import javax.inject.Inject

class EventsAdapter(var events: List<Event>) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

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
        val dateFormat = SimpleDateFormat("dd MMMM • EEEE • H:mm")
        holder.apply {
            title.text = event.name

            date.text = dateFormat.format(event.startsAt)
            place.text = event.location.address
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