package istu.edu.irnitu.model

import androidx.room.Database
import androidx.room.RoomDatabase
import istu.edu.irnitu.entity.Class
import istu.edu.irnitu.model.repository.ScheduleDao

@Database(entities = [Class::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getScheduleDao(): ScheduleDao
}