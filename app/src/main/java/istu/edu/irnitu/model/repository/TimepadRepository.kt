package istu.edu.irnitu.model.repository

import istu.edu.irnitu.entity.Event
import istu.edu.irnitu.model.data.TimepadApi


class TimepadRepository(
        private val api: TimepadApi
) {
    fun getEvents(limit: Int) {
        api.getEvents(limit, "starts_at", "description_short", 112730)
    }
}