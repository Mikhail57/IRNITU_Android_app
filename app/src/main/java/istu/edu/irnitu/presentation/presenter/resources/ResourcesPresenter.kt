package istu.edu.irnitu.presentation.presenter.resources

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import istu.edu.irnitu.model.repository.ResourcesRepository
import istu.edu.irnitu.presentation.view.resources.ResourcesView
import javax.inject.Inject

@InjectViewState
class ResourcesPresenter : MvpPresenter<ResourcesView>() {
    @Inject
    lateinit var repository: ResourcesRepository

    override fun onFirstViewAttach() {
        repository.getResources()
                .doOnSubscribe { viewState.showLoading(true) }
                .doAfterTerminate { viewState.showLoading(false) }
                .subscribe({
                    viewState.showResources(it)
                }, {
                    viewState.showLoadingError(it.localizedMessage)
                })
    }

}
