package istu.edu.irnitu.utils

import androidx.recyclerview.widget.DiffUtil
import istu.edu.irnitu.entity.News

val NEWS_DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(first: News, second: News): Boolean = first.id == second.id

    override fun areContentsTheSame(first: News, second: News): Boolean {
        return first.id == second.id
    }
}