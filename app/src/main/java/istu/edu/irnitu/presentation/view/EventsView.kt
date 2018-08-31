package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.Event

interface EventsView : MvpView {
    fun showLoading(isLoading: Boolean)
    fun showLoadingError(msg: String)
    fun showEvents(events: List<Event>)
}
