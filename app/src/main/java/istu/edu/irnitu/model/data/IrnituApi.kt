package istu.edu.irnitu.model.data

import io.reactivex.Single
import istu.edu.irnitu.entity.Class
import istu.edu.irnitu.entity.Faculty
import istu.edu.irnitu.entity.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IrnituApi {
    @GET("/news")
    fun getNews(@Query("page") page: Int): Single<List<News>>

    @GET("/schedule/groups")
    fun getGroups(): Single<List<Faculty>>

    @GET("/schedule/group/{group}")
    fun getGroupSchedule(@Path("group") group: String): Single<List<Class>>
}