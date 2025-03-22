package ignacio.guerendiain.uala.core.network

import ignacio.guerendiain.uala.core.domain.model.City

object APIModelMapper{
    fun buildCityFrom(cityAPI: CityAPI): City? {
        return cityAPI._id?.let { id ->
            City(
                id = id,
                name = cityAPI.name?:"",
                country = cityAPI.country?:"",
                lat = cityAPI.coord?.lat,
                lon = cityAPI.coord?.lon
            )
        }
    }
}
