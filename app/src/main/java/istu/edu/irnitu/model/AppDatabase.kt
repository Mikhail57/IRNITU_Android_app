package istu.edu.irnitu.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import istu.edu.irnitu.entity.Class
import istu.edu.irnitu.model.repository.ScheduleDao

@Database(entities = [Class::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getScheduleDao(): ScheduleDao
}