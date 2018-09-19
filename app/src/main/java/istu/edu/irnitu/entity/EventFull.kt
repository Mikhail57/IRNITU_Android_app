package istu.edu.irnitu.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class EventFull(
    @field:SerializedName("starts_at")
    val startsAt: Date,

    @field:SerializedName("description_html")
    val descriptionHtml: String,

    @field:SerializedName("moderation_status")
    val moderationStatus: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("categories")
    val categories: List<EventCategory>,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("poster_image")
    val image: EventImage,

    @field:SerializedName("location")
    val location: EventLocation
)