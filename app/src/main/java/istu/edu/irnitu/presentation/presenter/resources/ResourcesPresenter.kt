package istu.edu.irnitu.presentation.presenter.resources

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application
import istu.edu.irnitu.model.repository.ResourcesRepository
import istu.edu.irnitu.presentation.view.resources.ResourcesView
import javax.inject.Inject

@InjectViewState
class ResourcesPresenter : MvpPresenter<ResourcesView>() {
    @Inject
    lateinit var repository: ResourcesRepository

    private val disposable = CompositeDisposable()

    init {
        Application.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        disposable.add(repository.getResources()
                .doOnSubscribe { viewState.showLoading(true) }
                .doAfterTerminate { viewState.showLoading(false) }
                .subscribe({
                    viewState.showResources(it)
                }, {
                    viewState.showLoadingError(it.localizedMessage)
                }))
    }

    override fun onDestroy() {
        disposable.clear()
    }

}
