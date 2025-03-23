package ignacio.guerendiain.uala.core.network

import ignacio.guerendiain.uala.core.util.LoadingStatus

data class APICallResult(
    val status: LoadingStatus,
    val exception: Exception? = null,
    val httpStatusCode: Int? = null,
    val errorBody: String? = null
)