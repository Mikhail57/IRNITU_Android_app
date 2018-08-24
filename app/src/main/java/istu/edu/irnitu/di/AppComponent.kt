package istu.edu.irnitu.di

import android.content.Context
import dagger.Component
import istu.edu.irnitu.di.modules.ContextModule
import istu.edu.irnitu.di.modules.TimepadModule
import istu.edu.irnitu.presentation.presenter.events.EventsPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, TimepadModule::class])
interface AppComponent {
    fun getContext(): Context

    fun inject(eventsPresenter: EventsPresenter)
}