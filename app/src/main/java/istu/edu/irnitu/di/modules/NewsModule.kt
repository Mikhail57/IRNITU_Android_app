package istu.edu.irnitu.di.modules

import dagger.Module
import dagger.Provides
import istu.edu.irnitu.di.modules.common.SchedulersModule
import istu.edu.irnitu.model.data.IrnituApi
import istu.edu.irnitu.model.repository.NewsPagedRepository
import istu.edu.irnitu.model.system.SchedulersProvider
import javax.inject.Singleton

@Module(includes = [ApiModule::class, SchedulersModule::class])
class NewsModule {
    @Provides
    @Singleton
    fun provideNewsRepository(api: IrnituApi, schedulers: SchedulersProvider): NewsPagedRepository =
        NewsPagedRepository(api, schedulers)
}