package net.decodex.demo.data.database.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class LocationInfo(
    @Embedded
    val street: StreetInfo,
    val city: String,
    val state: String,
    @SerializedName("postcode")
    val postCode: String,
    @Embedded
    val coordinates: Coordinates,
    @SerializedName("timezone")
    @Embedded
    val timeZone: TimeZoneInfo
)