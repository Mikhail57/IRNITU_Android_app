package istu.edu.irnitu.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import istu.edu.irnitu.presentation.view.NewsPostView

@InjectViewState
class NewsPostPresenter(private val postId: Int) : MvpPresenter<NewsPostView>() {

}
