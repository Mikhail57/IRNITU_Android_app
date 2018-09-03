package istu.edu.irnitu.model.repository

import io.reactivex.Single
import istu.edu.irnitu.entity.Faculty
import istu.edu.irnitu.model.data.IrnituApi
import istu.edu.irnitu.model.system.SchedulersProvider

class ScheduleNetworkRepository(
        private val api: IrnituApi,
        private val schedulers: SchedulersProvider
) {
    fun getGroups(): Single<List<Faculty>> = api.getGroups()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun getScheduleForGroup(group: String) = api.getGroupSchedule(group)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
}