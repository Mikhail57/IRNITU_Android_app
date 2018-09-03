package istu.edu.irnitu.presentation.view

import com.arellomobile.mvp.MvpView
import istu.edu.irnitu.entity.Event

interface EventsView : MvpView, BaseLoadingView {
    fun showEvents(events: List<Event>)
}
