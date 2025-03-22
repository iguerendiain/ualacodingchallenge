package ignacio.guerendiain.uala.app

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.network.APICallResult
import ignacio.guerendiain.uala.core.util.LoadingStatus

data class MainState(
    val citiesResponse: APICallResult<List<City>>
){
    companion object {
        val DEFAULT = MainState(
            citiesResponse = APICallResult(LoadingStatus.LOADING)
        )
    }
}