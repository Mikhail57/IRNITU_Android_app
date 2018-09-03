package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.Resource

interface ResourcesView : MvpView, BaseLoadingView {
    fun showResources(resources: List<Resource>)
}
