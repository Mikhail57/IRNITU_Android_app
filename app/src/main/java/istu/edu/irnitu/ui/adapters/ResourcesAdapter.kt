package istu.edu.irnitu.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import istu.edu.irnitu.R
import istu.edu.irnitu.entity.Resource

class ResourcesAdapter(var resources: List<Resource>) : RecyclerView.Adapter<ResourcesAdapter.ResourcesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourcesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resource, parent, false)
        return ResourcesViewHolder(view)
    }

    override fun getItemCount(): Int = resources.size

    override fun onBindViewHolder(holder: ResourcesViewHolder, position: Int) {
        val resource = resources[position]
        holder.apply {
            image.setImageURI(resource.image)
            title.text = resource.title
        }
    }

    class ResourcesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: SimpleDraweeView = itemView.findViewById(R.id.resourceImage)
        val title: TextView = itemView.findViewById(R.id.resourceTitle)
    }
}