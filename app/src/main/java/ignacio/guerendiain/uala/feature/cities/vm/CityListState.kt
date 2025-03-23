package ignacio.guerendiain.uala.feature.cities.vm

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.util.LoadingStatus

data class CityListState(
    val loadingStatus: LoadingStatus,
    val listedCities: List<City>,
    val filterFavorites: Boolean,
    val searchQuery: String?
){
    companion object{
        val DEFAULT = CityListState(
            loadingStatus = LoadingStatus.LOADING,
            listedCities = listOf(),
            filterFavorites = false,
            searchQuery = null
        )
    }
}