package ignacio.guerendiain.uala.core.domain.repository

import ignacio.guerendiain.uala.core.domain.model.City

interface CityRepository{
    suspend fun downloadCities(): List<City>
    suspend fun storeCities(cities: List<City>)
    suspend fun listCities(): List<City>
    suspend fun searchCities(query: String): List<City>
}