package istu.edu.irnitu.entity

import com.google.gson.annotations.SerializedName

data class EventCategory(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)