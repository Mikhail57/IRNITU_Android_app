package istu.edu.irnitu.presentation.presenter

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import istu.edu.irnitu.Application
import istu.edu.irnitu.entity.News
import istu.edu.irnitu.model.datasource.NewsNetworkDataSourceFactory
import istu.edu.irnitu.model.repository.NewsPagedRepository
import istu.edu.irnitu.presentation.view.NewsView
import javax.inject.Inject

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    @Inject
    lateinit var newsPagedRepository: NewsPagedRepository

    private val newsNetworkDataSourceFactory: DataSource.Factory<Int, News>
    private val newsList: Observable<PagedList<News>>

    init {
        Application.appComponent.inject(this)
        newsNetworkDataSourceFactory = NewsNetworkDataSourceFactory(newsPagedRepository)
        newsList = RxPagedListBuilder(newsNetworkDataSourceFactory, 20).buildObservable()
    }

    override fun onFirstViewAttach() {
        viewState.showData(newsList)
    }
}
