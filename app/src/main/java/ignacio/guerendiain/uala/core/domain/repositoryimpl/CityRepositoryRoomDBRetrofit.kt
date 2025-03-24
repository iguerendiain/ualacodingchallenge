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
    override suspend fun downloadCities(): Pair<APICallResult, List<City>> {
        val response = runCall { cityCalls.getCities() }

        return Pair(
            response.first,
            response.second?.mapNotNull {  APIModelMapper.buildCityFrom(it) }?:listOf()
        )
    }

    override suspend fun storeCities(cities: List<City>) {
        db
            .cityDao()
            .apply {
                val favorites = getFavoriteIds()
                clearCities()
                storeCities(cities.map {
                    DBModelMapper.buildCityDBFrom(
                        it,
                        favorites.find { c -> c == it.id } != null
                    )
                })
            }
    }

    override suspend fun areCitiesDownloaded(): Boolean {
        return db.cityDao().getCityCount() > 0
    }

    override suspend fun favoriteCity(id: Long) {
        db.cityDao().favoriteCity(id)
    }

    override suspend fun unfavoriteCity(id: Long) {
        db.cityDao().unfavoriteCity(id)
    }

    override suspend fun getAllCities(): List<City> {
        return db
            .cityDao()
            .getAllCities()
            .map { DBModelMapper.buildCityFrom(it) }
    }

    override suspend fun getFavoriteCities(): List<City> {
        return db
            .cityDao()
            .getFavoriteCities()
            .map { DBModelMapper.buildCityFrom(it) }
    }

    override suspend fun filterCities(query: String): List<City> {
        return db
            .cityDao()
            .searchCities(query)
            .map { DBModelMapper.buildCityFrom(it) }
    }

    override suspend fun filterFavoriteCities(query: String): List<City> {
        return db
            .cityDao()
            .searchFavoriteCities(query)
            .map { DBModelMapper.buildCityFrom(it) }
    }

    override suspend fun getCityById(id: Long): City? {
        return db
            .cityDao()
            .getCityById(id)
            ?.let { DBModelMapper.buildCityFrom(it) }
    }
}