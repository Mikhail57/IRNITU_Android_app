package istu.edu.irnitu.model.data

import io.reactivex.Single
import istu.edu.irnitu.entity.News
import retrofit2.http.GET
import retrofit2.http.Query

interface IrnituApi {
    @GET("/news")
    fun getNews(@Query("page") page: Int): Single<List<News>>
}