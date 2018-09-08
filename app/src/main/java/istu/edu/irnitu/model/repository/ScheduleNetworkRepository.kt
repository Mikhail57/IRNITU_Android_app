package istu.edu.irnitu.model.repository

import io.reactivex.Single
import istu.edu.irnitu.entity.Class
import istu.edu.irnitu.entity.Faculty
import istu.edu.irnitu.model.data.IrnituApi
import istu.edu.irnitu.model.system.SchedulersProvider

class ScheduleNetworkRepository(
        private val api: IrnituApi,
        private val schedulers: SchedulersProvider
) : ScheduleRepository {
    override fun getGroupSchedule(group: String): Single<List<Class>> = api.getGroupSchedule(group)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    override fun getGroupScheduleForDay(group: String, day: Int): Single<List<Class>> =
            getGroupSchedule(group).map {
                it.filter { klass ->
                    day == klass.day || (day > 6 && (day - 6 == klass.day) && klass.everyWeek == 2)
                }
            }

    override fun getGroups(): Single<List<Faculty>> = api.getGroups()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun getScheduleForGroup(group: String) = api.getGroupSchedule(group)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
}