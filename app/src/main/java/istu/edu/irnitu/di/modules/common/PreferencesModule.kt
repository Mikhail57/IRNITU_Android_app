package istu.edu.irnitu.di.modules.common

import android.content.Context
import dagger.Module
import dagger.Provides
import istu.edu.irnitu.model.repository.PreferencesRepository
import istu.edu.irnitu.model.repository.SharedPreferencesRepository
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class PreferencesModule {
    @Provides
    @Singleton
    fun getPreferences(context: Context): PreferencesRepository =
        SharedPreferencesRepository(context)
}