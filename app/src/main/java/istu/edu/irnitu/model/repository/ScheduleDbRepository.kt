package istu.edu.irnitu.model.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import istu.edu.irnitu.entity.Class

@Dao
interface ScheduleDbRepository {

    @Query("SELECT * from $TABLE WHERE group_title LIKE :group ")
    fun getScheduleForGroup(group: String): Single<List<Class>>

    @Query("SELECT group_title FROM $TABLE")
    fun getGroups(): Single<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClasses(classes: List<Class>)

    companion object {
        const val TABLE = "schedule"
    }
}