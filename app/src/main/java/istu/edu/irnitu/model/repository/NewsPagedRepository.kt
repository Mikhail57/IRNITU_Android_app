package istu.edu.irnitu.model.repository

import io.reactivex.Single
import istu.edu.irnitu.entity.News
import istu.edu.irnitu.entity.NewsPost
import istu.edu.irnitu.model.data.IrnituApi
import istu.edu.irnitu.model.system.SchedulersProvider

class NewsPagedRepository(
    private val api: IrnituApi,
    private val schedulers: SchedulersProvider
) {
    fun getNews(page: Int): Single<List<News>> = api.getNews(page)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())

    fun getNewsPost(postId: Long): Single<NewsPost> = api.getNewsPost(postId)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
}