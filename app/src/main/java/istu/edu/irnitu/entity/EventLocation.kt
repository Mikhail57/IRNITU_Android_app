package istu.edu.irnitu.entity

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class EventLocation(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("coordinates")
    val coordinates: List<String?>? = null
)