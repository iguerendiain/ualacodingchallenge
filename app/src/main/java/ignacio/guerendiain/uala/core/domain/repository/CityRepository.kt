package ignacio.guerendiain.uala.core.domain.repository

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.network.APICallResult

interface CityRepository{
    suspend fun downloadCities(): APICallResult<List<City>>
    suspend fun storeCities(cities: List<City>)
    suspend fun listCities(): List<City>
    suspend fun searchCities(query: String): List<City>
    suspend fun areCitiesDownloaded(): Boolean
}