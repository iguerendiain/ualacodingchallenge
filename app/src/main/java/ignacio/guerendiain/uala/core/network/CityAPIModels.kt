package ignacio.guerendiain.uala.core.network

data class CityAPI(
    val country: String? = null,
    val name: String? = null,
    val _id: Long? = null,
    val coord: CityCoordAPI? = null
)

data class CityCoordAPI(
    val lon: Double? = null,
    val lat: Double? = null
)
