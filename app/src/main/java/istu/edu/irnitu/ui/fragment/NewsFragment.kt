package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.NewsView
import istu.edu.irnitu.presentation.presenter.NewsPresenter

import com.arellomobile.mvp.presenter.InjectPresenter

class NewsFragment : MvpAppCompatFragment(), NewsView {
    companion object {
        const val TAG = "NewsFragment"

        fun newInstance(): NewsFragment {
            val fragment: NewsFragment = NewsFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mNewsPresenter: NewsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
