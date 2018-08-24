package istu.edu.irnitu.model.data

import retrofit2.http.Field
import retrofit2.http.GET

interface TimepadApi {

    @GET("v1/events")
    fun getEvents(
            @Field("limit") limit: Int,
            @Field("sort") sort: String,
            @Field("fields") fields: String,
            @Field("organization_ids") organizationId: Int
    )
}