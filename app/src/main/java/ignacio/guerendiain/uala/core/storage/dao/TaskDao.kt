package ignacio.guerendiain.uala.core.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ignacio.guerendiain.uala.core.storage.model.CityDB

@Dao
interface CityDao {
    companion object{
        const val DEFAULT_SORT = "order by name,country"
    }

    @Insert
    fun storeCities(cities: List<CityDB>)

    @Query("delete from city")
    fun clearCities()

    @Query("select * from city $DEFAULT_SORT")
    fun getAllCities(): List<CityDB>

    @Query("select * from city where favorite = 1 and lower(name) like lower(:query) || '%' $DEFAULT_SORT")
    fun searchFavoriteCities(query: String): List<CityDB>

    @Query("select * from city where lower(name) like lower(:query) || '%' $DEFAULT_SORT")
    fun searchCities(query: String): List<CityDB>

    @Query("select count() from city")
    fun getCityCount(): Int

    @Query("select * from city where favorite = 1 $DEFAULT_SORT")
    fun getFavoriteCities(): List<CityDB>

    @Query("select _id from city where favorite = 1")
    fun getFavoriteIds(): List<Long>

    @Query("update city set favorite = 1 where _id = :id")
    fun favoriteCity(id: Long)

    @Query("update city set favorite = 0 where _id = :id")
    fun unfavoriteCity(id: Long)

    @Query("select * from city where _id = :id limit 1")
    fun getCityById(id: Long): CityDB?
}