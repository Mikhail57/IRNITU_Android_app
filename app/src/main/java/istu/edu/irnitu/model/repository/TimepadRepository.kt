package istu.edu.irnitu.model.repository

import io.reactivex.Single
import istu.edu.irnitu.entity.Event
import istu.edu.irnitu.model.data.TimepadApi
import istu.edu.irnitu.model.system.SchedulersProvider


class TimepadRepository(
        private val api: TimepadApi,
        private val schedulers: SchedulersProvider
) {
    fun getEvents(limit: Int): Single<List<Event>> {
        return api.getEvents(limit, "starts_at", "description_short", 112730)
                .map { it.values }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
    }
}