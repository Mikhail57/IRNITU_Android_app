package istu.edu.irnitu.utils

import com.google.gson.annotations.SerializedName

data class TimepadResponceWrapper<T>(

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("values")
    val values: List<T>
)