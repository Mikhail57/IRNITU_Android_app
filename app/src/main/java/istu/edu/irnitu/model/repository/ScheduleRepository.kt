package istu.edu.irnitu.model.repository

import io.reactivex.Completable
import io.reactivex.Single
import istu.edu.irnitu.entity.Class
import istu.edu.irnitu.entity.Faculty

interface ScheduleRepository {
    fun getGroupSchedule(group: String): Single<List<Class>>
    fun getGroupScheduleForDay(group: String, day: Int): Single<List<Class>>
    fun getGroups(): Single<List<Faculty>>
    fun insertSchedule(classes: List<Class>): Completable
}
