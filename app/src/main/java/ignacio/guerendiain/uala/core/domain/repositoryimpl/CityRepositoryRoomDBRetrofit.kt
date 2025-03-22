package ignacio.guerendiain.uala.core.domain.repositoryimpl

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.domain.repository.CityRepository
import ignacio.guerendiain.uala.core.network.CityCalls
import ignacio.guerendiain.uala.core.storage.MainDB

class CityRepositoryRoomDBRetrofit(
    private val db: MainDB,
    private val cityCalls: CityCalls
): CityRepository {
    override suspend fun downloadCities(): List<City> {
        return listOf()
    }

    override suspend fun storeCities(cities: List<City>) {
    }

    override suspend fun listCities(): List<City> {
        return listOf()
    }

    override suspend fun searchCities(query: String): List<City> {
        return listOf()
    }

}