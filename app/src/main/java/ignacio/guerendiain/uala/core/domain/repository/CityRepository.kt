package ignacio.guerendiain.uala.core.domain.repository

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.network.APICallResult

interface CityRepository{
    suspend fun downloadCities(): Pair<APICallResult, List<City>?>
    suspend fun storeCities(cities: List<City>)
    suspend fun areCitiesDownloaded(): Boolean
    suspend fun favoriteCity(id: Long)
    suspend fun unfavoriteCity(id: Long)
    suspend fun getAllCities(): List<City>
    suspend fun getFavoriteCities(): List<City>
    suspend fun filterCities(query: String): List<City>
    suspend fun filterFavoriteCities(query: String): List<City>
}