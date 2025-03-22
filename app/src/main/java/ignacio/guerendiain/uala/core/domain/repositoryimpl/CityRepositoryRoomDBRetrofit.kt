package ignacio.guerendiain.uala.core.domain.repositoryimpl

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.domain.repository.CityRepository
import ignacio.guerendiain.uala.core.network.APICallResult
import ignacio.guerendiain.uala.core.network.APIModelMapper
import ignacio.guerendiain.uala.core.network.CityCalls
import ignacio.guerendiain.uala.core.network.NetworkRepository
import ignacio.guerendiain.uala.core.network.runCall
import ignacio.guerendiain.uala.core.storage.DBModelMapper
import ignacio.guerendiain.uala.core.storage.MainDB

class CityRepositoryRoomDBRetrofit(
    private val db: MainDB,
    private val cityCalls: CityCalls
): CityRepository, NetworkRepository {
    override suspend fun downloadCities(): APICallResult<List<City>> {
        return runCall { cityCalls.getCities() }
            .map { apiList ->
                apiList
                    ?.mapNotNull { APIModelMapper.buildCityFrom(it) }
                    ?: listOf()
            }
    }

    override suspend fun storeCities(cities: List<City>) {
        db
            .cityDao()
            .apply {
                clearCities()
                storeCities(cities.map { DBModelMapper.buildCityDBFrom(it) })
            }
    }

    override suspend fun listCities(): List<City> {
        return db
            .cityDao()
            .listCities()
            .map { DBModelMapper.buildCityFrom(it) }
    }

    override suspend fun searchCities(query: String): List<City> {
        return listCities()
    }

    override suspend fun areCitiesDownloaded(): Boolean {
        return db.cityDao().getCityCount() > 0
    }
}