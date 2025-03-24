package ignacio.guerendiain.uala.feature.cities.vm

import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.util.LoadingStatus

data class MapState(
    val loadingStatus: LoadingStatus,
    val city: City?
){
    companion object{
        val DEFAULT = MapState(
            loadingStatus = LoadingStatus.LOADING,
            city = null
        )
    }
}