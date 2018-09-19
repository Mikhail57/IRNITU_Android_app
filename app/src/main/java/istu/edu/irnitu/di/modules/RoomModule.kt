package istu.edu.irnitu.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import istu.edu.irnitu.di.modules.common.ContextModule
import istu.edu.irnitu.model.AppDatabase
import istu.edu.irnitu.model.repository.ScheduleDao
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class RoomModule {
    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "irnitu-database"
    ).build()

    @Provides
    @Singleton
    fun providesScheduleDao(appDatabase: AppDatabase): ScheduleDao = appDatabase.getScheduleDao()
}