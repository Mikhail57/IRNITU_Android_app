package istu.edu.irnitu.ui.adapters

import androidx.paging.PagedListAdapter
import com.google.android.material.chip.Chip
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import istu.edu.irnitu.R
import istu.edu.irnitu.entity.News
import istu.edu.irnitu.utils.NEWS_DIFF_CALLBACK

class NewsAdapter : PagedListAdapter<News, NewsAdapter.NewsViewHolder>(NEWS_DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position) ?: return
        holder.bind(news)
    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: SimpleDraweeView = itemView.findViewById(R.id.newsImage)
        val title: TextView = itemView.findViewById(R.id.newsTitle)
        val shortDescription: TextView = itemView.findViewById(R.id.newsShortText)
        val date: TextView = itemView.findViewById(R.id.newsDate)
        val chip1: Chip = itemView.findViewById(R.id.newsChip1)
        val chip2: Chip = itemView.findViewById(R.id.newsChip2)

        fun bind(news: News) {
            image.setImageURI(news.image)
            title.text = news.title
            shortDescription.text = news.shortDesc
            date.text = news.date
            chip1.text = news.categories[0].title
            if (news.categories.size > 1)
                chip2.text = news.categories[1].title
            else
                chip2.visibility = View.GONE
        }
    }
}