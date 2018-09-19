package istu.edu.irnitu.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import istu.edu.irnitu.R
import istu.edu.irnitu.presentation.view.ResourcesView
import istu.edu.irnitu.presentation.presenter.ResourcesPresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import istu.edu.irnitu.entity.Resource
import istu.edu.irnitu.ui.adapters.ResourcesAdapter
import kotlinx.android.synthetic.main.fragment_resources.*

class ResourcesFragment : MvpAppCompatFragment(), ResourcesView {

    @InjectPresenter
    lateinit var mResourcesPresenter: ResourcesPresenter

    private lateinit var viewAdapter: ResourcesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var resources: List<Resource> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewAdapter = ResourcesAdapter(resources)
        viewManager = LinearLayoutManager(view.context)

        resourcesRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun showLoading(isLoading: Boolean) {
    }

    override fun showLoadingError(msg: String) {
    }

    override fun showResources(resources: List<Resource>) {
        this.resources = resources
        viewAdapter.resources = resources
        viewAdapter.notifyDataSetChanged()
    }

    companion object {
        const val TAG = "ResourcesFragment"

        fun newInstance(): ResourcesFragment {
            val fragment: ResourcesFragment = ResourcesFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
