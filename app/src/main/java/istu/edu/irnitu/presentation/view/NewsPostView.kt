package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.NewsPost

interface NewsPostView : MvpView {
    fun showLoading(isLoading: Boolean)

    fun showError(error: String)

    fun showNewsPost(post: NewsPost)
}
