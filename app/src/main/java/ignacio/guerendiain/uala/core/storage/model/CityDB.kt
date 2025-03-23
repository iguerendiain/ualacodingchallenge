package ignacio.guerendiain.uala.core.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityDB(
    @PrimaryKey val _id: Long = 0,
    val name: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?,
    val favorite: Boolean
)