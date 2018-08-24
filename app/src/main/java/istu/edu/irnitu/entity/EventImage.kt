package istu.edu.irnitu.entity

import com.google.gson.annotations.SerializedName

data class EventImage(

	@field:SerializedName("default_url")
	val defaultUrl: String,

	@field:SerializedName("uploadcare_url")
	val uploadcareUrl: String
)