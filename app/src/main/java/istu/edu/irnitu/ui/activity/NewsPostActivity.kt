package istu.edu.irnitu.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.NewsPostView
import istu.edu.irnitu.presentation.presenter.NewsPostPresenter

import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.ProvidePresenter
import istu.edu.irnitu.entity.NewsPost
import kotlinx.android.synthetic.main.activity_news_post.*


class NewsPostActivity : MvpAppCompatActivity(), NewsPostView {
    override fun showLoadin(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNewsPost(post: NewsPost) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val TAG = "NewsPostActivity"
        private const val ARG_POST_ID = "post_id"
        fun getIntent(context: Context, postId: Long): Intent =
            Intent(context, NewsPostActivity::class.java).apply {
                putExtra(ARG_POST_ID, postId)
            }
    }

    @InjectPresenter
    lateinit var mNewsPostPresenter: NewsPostPresenter

    @ProvidePresenter
    fun providePresenter(): NewsPostPresenter {
        return NewsPostPresenter(intent.getLongExtra(ARG_POST_ID, 0))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_post)

        content.text = "Very Long Text\n".repeat(500)
    }
}
