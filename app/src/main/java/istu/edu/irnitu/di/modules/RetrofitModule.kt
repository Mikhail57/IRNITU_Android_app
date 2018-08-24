package istu.edu.irnitu.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit = builder.baseUrl("http://api.portalgkh.ru/").build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(factory: Converter.Factory, client: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(factory)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()
}