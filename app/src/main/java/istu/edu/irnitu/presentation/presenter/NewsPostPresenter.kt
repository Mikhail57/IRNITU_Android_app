package istu.edu.irnitu.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application
import istu.edu.irnitu.model.repository.NewsPagedRepository
import istu.edu.irnitu.presentation.view.NewsPostView
import javax.inject.Inject

@InjectViewState
class NewsPostPresenter(private val postId: Long) : MvpPresenter<NewsPostView>() {

    @Inject
    lateinit var repository: NewsPagedRepository

    private val disposable = CompositeDisposable()

    init {
        Application.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        disposable.add(
            repository
                .getNewsPost(postId)
                .doOnSubscribe { viewState.showLoading(true) }
                .doFinally { viewState.showLoading(false) }
                .subscribe({
                    viewState.showNewsPost(it)
                }, {
                    viewState.showError(it.localizedMessage)
                })
        )
    }

    override fun onDestroy() {
        disposable.dispose()
    }
}
