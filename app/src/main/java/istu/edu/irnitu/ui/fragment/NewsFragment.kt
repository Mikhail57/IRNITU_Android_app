package istu.edu.irnitu.ui.fragment

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.NewsView
import istu.edu.irnitu.presentation.presenter.NewsPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.entity.News
import istu.edu.irnitu.ui.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : MvpAppCompatFragment(), NewsView {

    @InjectPresenter
    lateinit var mNewsPresenter: NewsPresenter

    private lateinit var viewAdapter: NewsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewAdapter = NewsAdapter()
        viewManager = LinearLayoutManager(context)

        newsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

    override fun showData(pagedNews: Observable<PagedList<News>>) {
        Log.d(TAG, "SHOW DATA")
        disposable.add(pagedNews.subscribe { viewAdapter.submitList(it) })
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    companion object {
        const val TAG = "NewsFragment"

        fun newInstance(): NewsFragment {
            val fragment: NewsFragment = NewsFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
