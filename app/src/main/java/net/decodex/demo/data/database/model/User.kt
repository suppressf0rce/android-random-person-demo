package net.decodex.demo.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    val gender: String,
    @Embedded
    val name: NameInfo,
    @Embedded(prefix = "location_")
    val location: LocationInfo,
    @PrimaryKey(autoGenerate = false)
    val email: String,
    @Embedded
    @SerializedName("login")
    val loginInfo: LoginInfo,
    @Embedded(prefix = "dob_")
    @SerializedName("dob")
    val dateOfBirthInfo: DateInfo,
    @Embedded(prefix = "registered_")
    val registered: DateInfo,
    val phone: String,
    val cell: String,
    @Embedded
    val id: IdInfo,
    @Embedded
    val picture: PictureInfo,
    @SerializedName("nat")
    val nationality: String
) {
    var isFavorite: Boolean = false
}