package istu.edu.irnitu.model.repository

import androidx.room.*
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Single
import istu.edu.irnitu.entity.Class
import istu.edu.irnitu.entity.Faculty
import istu.edu.irnitu.model.system.SchedulersProvider

class ScheduleDbRepository(
    private val scheduleDao: ScheduleDao,
    private val schedulers: SchedulersProvider
) : ScheduleRepository {
    override fun getGroupSchedule(group: String): Single<List<Class>> {
        return scheduleDao.getScheduleForGroup(group)
            .doOnSuccess {
                Log.w("ScheduleDbRepository", "getGroupSchedule: $it")
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getGroupScheduleForDay(group: String, day: Int): Single<List<Class>> {
        return scheduleDao.getScheduleForGroup(group).map {
            it.filter { klass ->
                Log.w("ScheduleDbRepository", "day: $day, class.day=${klass.day}")
                day == klass.day || (day > 7 && (day - 7 == klass.day) && klass.everyWeek == 2)
            }
        }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getGroups(): Single<List<Faculty>> {
        return scheduleDao.getAll().map {
            it.groupBy { klass -> klass.faculty }
                .mapValues { classes ->
                    classes
                        .value
                        .groupBy { it.course }
                        .mapValues { it.value.map { it.group } }
                }
                .map { Faculty(it.key, it.value) }
        }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun insertSchedule(classes: List<Class>): Completable {
        return Single.fromCallable {
            scheduleDao.insertAll(classes)
        }
            .ignoreElement()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }


}

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM $TABLE")
    fun getAll(): Single<List<Class>>

    @Query("SELECT * from $TABLE WHERE group_title LIKE :group ")
    fun getScheduleForGroup(group: String): Single<List<Class>>

    @Query("SELECT group_title FROM $TABLE")
    fun getGroups(): Single<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg classes: Class)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(classes: List<Class>)

    @Delete
    fun delete(klass: Class)

    companion object {
        const val TABLE = "schedule"
    }
}