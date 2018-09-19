package istu.edu.irnitu.model.datasource

import android.annotation.SuppressLint
import android.arch.paging.PageKeyedDataSource
import istu.edu.irnitu.entity.News
import istu.edu.irnitu.model.repository.NewsPagedRepository

@SuppressLint("CheckResult")
class NewsDataSource(
    private val repository: NewsPagedRepository
) : PageKeyedDataSource<Int, News>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, News>
    ) {
        repository.getNews(FIRST_PAGE).subscribe({
            callback.onResult(it, null, FIRST_PAGE + 1)
        }, {})
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        repository.getNews(params.key).subscribe { news ->
            callback.onResult(news, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        repository.getNews(params.key).subscribe({
            val adjacentKey = (if (params.key > 1) params.key - 1 else null)?.toInt()
            callback.onResult(it, adjacentKey)
        }, {})
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}