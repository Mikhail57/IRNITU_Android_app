package istu.edu.irnitu.presentation.presenter.events

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import istu.edu.irnitu.Application
import istu.edu.irnitu.model.repository.EventsRepository
import istu.edu.irnitu.presentation.view.events.EventsView
import javax.inject.Inject

@InjectViewState
class EventsPresenter : MvpPresenter<EventsView>() {
    @Inject
    lateinit var eventsRepository: EventsRepository

    init {
        Application.appComponent.inject(this)
        Log.i("EventsPresenter", "INIT")
    }

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        eventsRepository
                .getEvents(10)
                .doOnSubscribe { viewState.showLoading(true) }
                .doAfterTerminate { viewState.showLoading(false) }
                .subscribe({
                    viewState.showEvents(it)
                }, {
                    viewState.showLoadingError(it.localizedMessage)
                })
    }
}
