package ignacio.guerendiain.uala.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ignacio.guerendiain.uala.core.domain.repository.CityRepository
import ignacio.guerendiain.uala.core.network.APICallResult
import ignacio.guerendiain.uala.core.ui.dialog.APIResultErrorDialog
import ignacio.guerendiain.uala.core.util.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val cityRepository: CityRepository
): ViewModel(){

    private val _state = MutableStateFlow(MainState.DEFAULT)
    val state = _state.asStateFlow()

    fun initApp(){
        if (state.value.citiesResponse.response.isNullOrEmpty()) {
            _state.update { MainState(citiesResponse = APICallResult(LoadingStatus.LOADING)) }
            viewModelScope.launch(Dispatchers.IO) {
                val areCitiesDownloaded = cityRepository.areCitiesDownloaded()

                if (!areCitiesDownloaded) {
                    val downloadedCitiesResult = cityRepository.downloadCities()

                    if (downloadedCitiesResult.status == LoadingStatus.SUCCESS) {
                        downloadedCitiesResult.response?.let {
                            cityRepository.storeCities(it)
                        }
                        loadCitiesFromDB()
                    } else
                        _state.update { MainState(citiesResponse = downloadedCitiesResult) }
                } else
                    loadCitiesFromDB()
            }
        }
    }

    private suspend fun loadCitiesFromDB(){
        val storedCities = cityRepository.listCities()
        _state.update { MainState(citiesResponse = APICallResult(
            status = LoadingStatus.SUCCESS,
            response = storedCities
        ))}
    }
}