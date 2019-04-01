package istu.edu.irnitu.ui.adapters

import androidx.paging.PagedListAdapter
import com.google.android.material.chip.Chip
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.chip.ChipGroup
import istu.edu.irnitu.R
import istu.edu.irnitu.entity.News
import istu.edu.irnitu.utils.NEWS_DIFF_CALLBACK
import istu.edu.irnitu.utils.OnItemClickListener

class NewsAdapter : PagedListAdapter<News, NewsAdapter.NewsViewHolder>(NEWS_DIFF_CALLBACK) {

    private var onItemClickListener: OnItemClickListener<Int, News>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position) ?: return
        holder.onClickListener = View.OnClickListener {
            onItemClickListener?.onClick(it, position, news)
        }
        holder.bind(news)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<Int, News>) {
        onItemClickListener = listener
    }

    fun hasOnClickListener(): Boolean = onItemClickListener == null


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val image: SimpleDraweeView = itemView.findViewById(R.id.newsImage)
        val title: TextView = itemView.findViewById(R.id.newsTitle)
        val shortDescription: TextView = itemView.findViewById(R.id.newsShortText)
        val date: TextView = itemView.findViewById(R.id.newsDate)
        val chipGroup: ChipGroup = itemView.findViewById(R.id.chipGroup)

        var onClickListener: View.OnClickListener? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(news: News) {
            image.setImageURI(news.image)
            title.text = news.title
            shortDescription.text = news.shortDesc
            date.text = news.date
            news.categories.forEach {
                chipGroup.addView(Chip(itemView.context).apply { text = it.title })
            }
        }

        override fun onClick(view: View?) {
            onClickListener?.onClick(view)
        }
    }
}