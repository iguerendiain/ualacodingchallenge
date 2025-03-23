package ignacio.guerendiain.uala.app

import ignacio.guerendiain.uala.core.network.APICallResult
import ignacio.guerendiain.uala.core.util.LoadingStatus

data class MainState(
    val loadingResult: APICallResult
){
    companion object {
        val DEFAULT = MainState(
            loadingResult = APICallResult(LoadingStatus.LOADING)
        )
    }
}