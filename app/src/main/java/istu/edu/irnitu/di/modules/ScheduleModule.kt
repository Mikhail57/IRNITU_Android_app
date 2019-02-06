package istu.edu.irnitu.di.modules

import androidx.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import istu.edu.irnitu.di.modules.common.ContextModule
import istu.edu.irnitu.di.modules.common.RetrofitModule
import istu.edu.irnitu.di.modules.common.SchedulersModule
import istu.edu.irnitu.model.AppDatabase
import istu.edu.irnitu.model.data.IrnituApi
import istu.edu.irnitu.model.repository.ScheduleDao
import istu.edu.irnitu.model.repository.ScheduleDbRepository
import istu.edu.irnitu.model.repository.ScheduleNetworkRepository
import istu.edu.irnitu.model.repository.ScheduleRepository
import istu.edu.irnitu.model.system.SchedulersProvider
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RoomModule::class, SchedulersModule::class, RetrofitModule::class, ContextModule::class])
class ScheduleModule {
    @Provides
    @Singleton
    @Named("network")
    fun providesScheduleNetworkRepository(
        api: IrnituApi,
        schedulers: SchedulersProvider
    ): ScheduleRepository = ScheduleNetworkRepository(api, schedulers)

    @Provides
    @Singleton
    @Named("db")
    fun providesScheduleDbRepository(
        scheduleDao: ScheduleDao,
        schedulers: SchedulersProvider
    ): ScheduleRepository = ScheduleDbRepository(
        scheduleDao,
        schedulers
    )
}