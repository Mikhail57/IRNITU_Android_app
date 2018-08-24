package istu.edu.irnitu.di

import android.content.Context
import dagger.Component
import istu.edu.irnitu.presentation.presenter.events.EventsPresenter

@Component(modules = [])
interface AppComponent {
    fun getContext(): Context

    fun inject(eventsPresenter: EventsPresenter)
}