package ignacio.guerendiain.uala.core.domain.model

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val lat: Double?,
    val lon: Double?
)