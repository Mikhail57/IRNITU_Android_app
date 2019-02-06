package istu.edu.irnitu.model.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import istu.edu.irnitu.entity.News
import istu.edu.irnitu.model.repository.NewsPagedRepository

class NewsNetworkDataSourceFactory(
    private val newsPagedRepository: NewsPagedRepository
) : DataSource.Factory<Int, News>() {
    private val newsLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, News>> =
        MutableLiveData()

    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(newsPagedRepository)
        newsLiveDataSource.postValue(newsDataSource)
        return newsDataSource
    }
}