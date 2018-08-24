package istu.edu.irnitu.ui.fragment.resources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.resources.ResourcesView
import istu.edu.irnitu.presentation.presenter.resources.ResourcesPresenter

import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class ResourcesFragment : MvpAppCompatFragment(), ResourcesView {
    companion object {
        const val TAG = "ResourcesFragment"

        fun newInstance(): ResourcesFragment {
            val fragment: ResourcesFragment = ResourcesFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mResourcesPresenter: ResourcesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_resources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
