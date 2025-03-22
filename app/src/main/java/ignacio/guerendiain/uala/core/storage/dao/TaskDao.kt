package ignacio.guerendiain.uala.core.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ignacio.guerendiain.uala.core.storage.model.CityDB

@Dao
interface CityDao {

    @Insert
    fun storeCities(cities: List<CityDB>)

    @Query("delete from city")
    fun clearCities()

    @Query("select * from city")
    fun listCities(): List<CityDB>

    @Query("select count() from city")
    fun getCityCount(): Int
}