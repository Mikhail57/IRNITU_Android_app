package istu.edu.irnitu.entity

import com.google.gson.annotations.SerializedName

data class Event(
        @field:SerializedName("starts_at")
        val startsAt: String,

        @field:SerializedName("description_short")
        val descriptionShort: String,

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
        val image: EventImage
)