package istu.edu.irnitu.presentation.view.resources

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.Resource

interface ResourcesView : MvpView {
    fun showLoading(isLoading: Boolean)
    fun showLoadingError(msg: String)
    fun showResources(resource: List<Resource>)

}
