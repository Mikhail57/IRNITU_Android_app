package istu.edu.irnitu.presentation.view

import androidx.paging.PagedList
import com.arellomobile.mvp.MvpView
import io.reactivex.Observable
import istu.edu.irnitu.entity.News

interface NewsView : MvpView {
    fun showData(pagedNews: Observable<PagedList<News>>)
}
