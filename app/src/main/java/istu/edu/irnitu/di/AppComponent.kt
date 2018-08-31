package istu.edu.irnitu.di

import android.content.Context
import dagger.Component
import istu.edu.irnitu.MainActivity
import istu.edu.irnitu.di.modules.ContextModule
import istu.edu.irnitu.di.modules.NavigationModule
import istu.edu.irnitu.di.modules.ResourcesModule
import istu.edu.irnitu.di.modules.TimepadModule
import istu.edu.irnitu.presentation.presenter.EventsPresenter
import istu.edu.irnitu.presentation.presenter.ResourcesPresenter
import istu.edu.irnitu.ui.adapters.EventsAdapter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class, NavigationModule::class,
    TimepadModule::class, ResourcesModule::class
])
interface AppComponent {
    fun getContext(): Context

    fun inject(mainActivity: MainActivity)
    fun inject(eventsPresenter: EventsPresenter)
    fun inject(eventsPresenter: ResourcesPresenter)
    fun inject(eventsAdapter: EventsAdapter)
}