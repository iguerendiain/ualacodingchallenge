package ignacio.guerendiain.uala.feature.cities.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ignacio.guerendiain.uala.core.domain.repository.CityRepository
import ignacio.guerendiain.uala.core.util.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CityListViewModel(
    private val cityRepository: CityRepository
): ViewModel() {

    private val _state = MutableStateFlow(CityListState.DEFAULT)
    val state = _state.asStateFlow()

    fun toggleFilterFavories(){
        _state.update { it.copy(filterFavorites = !it.filterFavorites) }
    }

    fun setSearchQuery(query: String?){
        _state.update { it.copy(searchQuery = if (query.isNullOrBlank()) null else query) }
    }

    fun filterCities(){
        _state.update { it.copy(loadingStatus = LoadingStatus.LOADING) }

        viewModelScope.launch(Dispatchers.IO) {
            val favorites = state.value.filterFavorites
            val query = state.value.searchQuery

            val listedCities = when{
                favorites && query!=null -> cityRepository.filterFavoriteCities(query)
                favorites && query==null -> cityRepository.getFavoriteCities()
                !favorites && query!=null -> cityRepository.filterCities(query)
                else -> cityRepository.getAllCities()
            }

            _state.update { it.copy(
                loadingStatus = LoadingStatus.SUCCESS,
                listedCities = listedCities
            ) }
        }
    }

    fun toggleFavorite(id: Long){
        var currentFavorite: Boolean = false
        val updatedListedCities = state.value.listedCities.map {
            if (it.id == id){
                currentFavorite = it.isFavorite
                it.copy(isFavorite = !it.isFavorite)
            } else it
        }

        _state.update { it.copy(listedCities = updatedListedCities) }

        viewModelScope.launch(Dispatchers.IO){
            if (currentFavorite)
                cityRepository.unfavoriteCity(id)
            else
                cityRepository.favoriteCity(id)
        }
    }
}
