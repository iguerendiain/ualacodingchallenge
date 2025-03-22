package ignacio.guerendiain.uala.core.network

import ignacio.guerendiain.uala.core.util.LoadingStatus

data class APICallResult<T>(
    val status: LoadingStatus,
    val response: T? = null,
    val exception: Exception? = null,
    val httpStatusCode: Int? = null,
    val errorBody: String? = null
){
    fun <E>map(mapper: (input: T?) -> E): APICallResult<E> {
        return APICallResult(
            status = this.status,
            response = mapper(this.response),
            exception = this.exception,
            httpStatusCode = this.httpStatusCode,
            errorBody = this.errorBody
        )
    }
}