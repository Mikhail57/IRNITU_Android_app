package istu.edu.irnitu.model.data

import istu.edu.irnitu.entity.Event
import istu.edu.irnitu.utils.TimepadResponseWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TimepadApi {

    @GET("v1/events")
    fun getEvents(
        @Query("limit") limit: Int,
        @Query("sort") sort: String,
        @Query("fields") fields: String,
        @Query("organization_ids") organizationId: Int
    ): Single<TimepadResponseWrapper<Event>>
}