package istu.edu.irnitu.di.modules.common

import dagger.Module
import dagger.Provides
import istu.edu.irnitu.model.system.AppSchedulers
import istu.edu.irnitu.model.system.SchedulersProvider
import javax.inject.Singleton

@Module
class SchedulersModule {
    @Provides
    @Singleton
    fun provideSchedulers(): SchedulersProvider = AppSchedulers()
}