package ignacio.guerendiain.uala.core.storage

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.storage.model.CityDB

object DBModelMapper{
    fun buildCityFrom(cityDB: CityDB) = City(
        id = cityDB._id,
        name = cityDB.name?:"",
        country = cityDB.country?:"",
        lat = cityDB.lat,
        lon = cityDB.lon,
        isFavorite = cityDB.favorite
    )

    fun buildCityDBFrom(city: City, favorite: Boolean) = CityDB(
        _id = city.id,
        name = city.name,
        country = city.country,
        lat = city.lat,
        lon = city.lon,
        favorite = favorite
    )
}