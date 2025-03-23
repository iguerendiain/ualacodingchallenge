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
    fun getAllCities(): List<CityDB>

    @Query("select * from city where favorite == 1 and lower(name) like lower(:query) || '%'")
    fun searchFavoriteCities(query: String): List<CityDB>

    @Query("select * from city where lower(name) like lower(:query) || '%'")
    fun searchCities(query: String): List<CityDB>

    @Query("select count() from city")
    fun getCityCount(): Int

    @Query("select * from city where favorite == 1")
    fun getFavoriteCities(): List<CityDB>

    @Query("select _id from city where favorite == 1")
    fun getFavoriteIds(): List<Long>

    @Query("update city set favorite = 1 where _id = :id")
    fun favoriteCity(id: Long)

    @Query("update city set favorite = 0 where _id = :id")
    fun unfavoriteCity(id: Long)
}