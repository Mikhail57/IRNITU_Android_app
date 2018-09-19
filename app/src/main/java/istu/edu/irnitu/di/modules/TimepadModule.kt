package istu.edu.irnitu.di.modules

import dagger.Module
import dagger.Provides
import istu.edu.irnitu.di.modules.common.SchedulersModule
import istu.edu.irnitu.model.data.TimepadApi
import istu.edu.irnitu.model.repository.EventsRepository
import istu.edu.irnitu.model.system.SchedulersProvider
import javax.inject.Singleton

@Module(includes = [ApiModule::class, SchedulersModule::class])
class TimepadModule {
    @Provides
    @Singleton
    fun provideEventsRepository(api: TimepadApi, schedulers: SchedulersProvider): EventsRepository =
        EventsRepository(api, schedulers)
}