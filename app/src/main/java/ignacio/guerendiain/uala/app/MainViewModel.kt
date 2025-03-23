package ignacio.guerendiain.uala.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ignacio.guerendiain.uala.core.domain.repository.CityRepository
import ignacio.guerendiain.uala.core.network.APICallResult
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

    fun downloadCities(){
        _state.update { MainState(loadingResult = APICallResult(LoadingStatus.LOADING)) }
        viewModelScope.launch(Dispatchers.IO) {
            val areCitiesDownloaded = cityRepository.areCitiesDownloaded()

            if (!areCitiesDownloaded) {
                val downloadedCitiesResponse = cityRepository.downloadCities()
                val downloadedCities = downloadedCitiesResponse.second
                val downloadedCitiesResult = downloadedCitiesResponse.first

                if (downloadedCitiesResult.status == LoadingStatus.SUCCESS)
                    downloadedCities?.let { cityRepository.storeCities(it) }

                _state.update { MainState(loadingResult = downloadedCitiesResult) }
            }else
                _state.update { MainState(loadingResult = APICallResult(LoadingStatus.SUCCESS)) }
        }
    }
}