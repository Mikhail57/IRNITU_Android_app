package istu.edu.irnitu.di

import android.content.Context
import dagger.Component
import istu.edu.irnitu.MainActivity
import istu.edu.irnitu.di.modules.*
import istu.edu.irnitu.di.modules.common.ContextModule
import istu.edu.irnitu.di.modules.common.PreferencesModule
import istu.edu.irnitu.presentation.presenter.*
import istu.edu.irnitu.ui.adapters.EventsAdapter
import istu.edu.irnitu.ui.fragment.ScheduleDayVPFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Common modules
        ContextModule::class, NavigationModule::class, RoomModule::class, PreferencesModule::class,
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
    fun inject(schedulePresenter: SchedulePresenter)
    fun inject(scheduleDayVPFragment: ScheduleDayVPFragment)
    fun inject(settingsPresenter: SettingsPresenter)
    fun inject(scheduleDayViewPagerPresenter: ScheduleDayViewPagerPresenter)
}