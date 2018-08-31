package istu.edu.irnitu.utils

import android.support.v7.util.DiffUtil
import istu.edu.irnitu.entity.News

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(first: News, second: News): Boolean = first.id == second.id

    override fun areContentsTheSame(first: News, second: News): Boolean {
        return first.id == second.id
    }

}