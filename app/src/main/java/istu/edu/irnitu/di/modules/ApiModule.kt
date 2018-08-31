package istu.edu.irnitu.di.modules

import dagger.Module
import dagger.Provides
import istu.edu.irnitu.model.data.IrnituApi
import istu.edu.irnitu.model.data.TimepadApi
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiModule {
    @Provides
    @Singleton
    fun provideTimepadApi(@Named("timepad") retrofit: Retrofit): TimepadApi
            = retrofit.create(TimepadApi::class.java)

    @Provides
    @Singleton
    fun provideIrnituApi(@Named("istu") retrofit: Retrofit): IrnituApi
            = retrofit.create(IrnituApi::class.java)

}