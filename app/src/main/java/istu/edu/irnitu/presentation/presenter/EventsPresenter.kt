package istu.edu.irnitu.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import istu.edu.irnitu.Application
import istu.edu.irnitu.model.repository.EventsRepository
import istu.edu.irnitu.presentation.view.EventsView
import javax.inject.Inject

@InjectViewState
class EventsPresenter : MvpPresenter<EventsView>() {
    @Inject
    lateinit var eventsRepository: EventsRepository

    private val disposable = CompositeDisposable()

    init {
        Application.appComponent.inject(this)
        Log.d("EventsPresenter", "INIT")
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.add(eventsRepository
            .getEvents(20)
            .doOnSubscribe { viewState.showLoading(true) }
            .doAfterTerminate { viewState.showLoading(false) }
            .subscribe({
                viewState.showEvents(it)
            }, {
                viewState.showLoadingError(it.localizedMessage)
            })
        )
    }

    override fun onDestroy() {
        disposable.clear()
    }
}
