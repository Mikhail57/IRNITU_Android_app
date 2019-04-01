package istu.edu.irnitu.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View.*
import android.widget.Toast

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.NewsPostView
import istu.edu.irnitu.presentation.presenter.NewsPostPresenter

import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import com.google.android.material.chip.Chip
import istu.edu.irnitu.entity.NewsPost
import istu.edu.irnitu.utils.addSliders
import kotlinx.android.synthetic.main.activity_news_post.*
import kotlinx.android.synthetic.main.part_chip_group.*


class NewsPostActivity : MvpAppCompatActivity(), NewsPostView {

    @InjectPresenter
    lateinit var mNewsPostPresenter: NewsPostPresenter

    @ProvidePresenter
    fun providePresenter(): NewsPostPresenter {
        return NewsPostPresenter(intent.getLongExtra(ARG_POST_ID, 0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_post)
//        toolbar.title = " "
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            newsScroll.visibility = INVISIBLE
        } else {
            newsScroll.visibility = VISIBLE
        }
    }

    override fun showError(error: String) {
        Toast.makeText(applicationContext, "Произошла ошибка: $error", Toast.LENGTH_SHORT).show()
    }

    override fun showNewsPost(post: NewsPost) {
        val contentReplaced = post.content.replace("\n", "<br>")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            content.text = Html.fromHtml(contentReplaced, Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            content.text = Html.fromHtml(contentReplaced)
        }
        content.movementMethod = LinkMovementMethod.getInstance()
        appBarImage.setImageURI(post.mainImageUrl)

        newsTitle.text = post.title
        toolbar.title = post.title
        collapsingToolbar.title = post.title

        post.categories.forEach {
            chipGroup.addView(Chip(this).apply { text = it.title })
        }

        val slides = post.images.map {
            DefaultSliderView(this).apply {
                image(it.full)
                scaleType = BaseSliderView.ScaleType.Fit
            }
        }
        if (slides.isEmpty())
            slider.visibility = GONE
        else
            slider.addSliders(slides)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val TAG = "NewsPostActivity"
        private const val ARG_POST_ID = "post_id"
        fun getIntent(context: Context, postId: Long): Intent =
            Intent(context, NewsPostActivity::class.java).apply {
                putExtra(ARG_POST_ID, postId)
            }
    }
}
