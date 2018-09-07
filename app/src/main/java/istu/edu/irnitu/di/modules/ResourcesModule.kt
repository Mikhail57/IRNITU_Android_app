package istu.edu.irnitu.di.modules

import dagger.Module
import dagger.Provides
import istu.edu.irnitu.di.modules.common.SchedulersModule
import istu.edu.irnitu.model.repository.ResourcesRepository
import istu.edu.irnitu.model.repository.ResourcesStubRepository
import istu.edu.irnitu.model.system.SchedulersProvider
import javax.inject.Singleton

@Module(includes = [SchedulersModule::class])
class ResourcesModule {
    @Provides
    @Singleton
    fun provideLocalResourcesRepository(schedulers: SchedulersProvider): ResourcesRepository
            = ResourcesStubRepository(schedulers)
}