package istu.edu.irnitu.di

import android.content.Context
import dagger.Component
import istu.edu.irnitu.MainActivity
import istu.edu.irnitu.di.modules.*
import istu.edu.irnitu.di.modules.common.ContextModule
import istu.edu.irnitu.presentation.presenter.EventsPresenter
import istu.edu.irnitu.presentation.presenter.MainScreenPresenter
import istu.edu.irnitu.presentation.presenter.NewsPresenter
import istu.edu.irnitu.presentation.presenter.ResourcesPresenter
import istu.edu.irnitu.ui.adapters.EventsAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Common modules
        ContextModule::class, NavigationModule::class, RoomModule::class,
        // Screen-specific modules
        TimepadModule::class, ResourcesModule::class, NewsModule::class, ScheduleModule::class
    ]
)
interface AppComponent {
    fun getContext(): Context

    fun inject(mainActivity: MainActivity)
    fun inject(eventsPresenter: EventsPresenter)
    fun inject(resourcesPresenter: ResourcesPresenter)
    fun inject(newsPresenter: NewsPresenter)
    fun inject(eventsAdapter: EventsAdapter)
    fun inject(mainScreenPresenter: MainScreenPresenter)
}