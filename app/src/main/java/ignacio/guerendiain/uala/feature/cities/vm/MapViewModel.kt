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

class MapViewModel(
    private val cityRepository: CityRepository
): ViewModel() {

    private val _state = MutableStateFlow(MapState.DEFAULT)
    val state = _state.asStateFlow()

    fun loadCity(id: Long){
        _state.update { it.copy(loadingStatus = LoadingStatus.LOADING) }

        viewModelScope.launch(Dispatchers.IO) {
            val city = cityRepository.getCityById(id)

            if (city!=null){
                _state.update { it.copy(
                    loadingStatus = LoadingStatus.SUCCESS,
                    city = city
                )}
            }else{
                _state.update { it.copy(loadingStatus = LoadingStatus.ERROR) }
            }
        }
    }
}