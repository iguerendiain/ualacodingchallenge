package ignacio.guerendiain.uala.core.network

import ignacio.guerendiain.uala.core.util.LoadingStatus
import retrofit2.Response

interface NetworkRepository

suspend fun<T> NetworkRepository.runCall(
    call: suspend () -> Response<T>
): APICallResult<T> {
    try {
        val response = call()

        return if (response.isSuccessful) APICallResult(
            status = LoadingStatus.SUCCESS,
            response = response.body(),
            httpStatusCode = response.code()
        ) else APICallResult(
            status = LoadingStatus.ERROR,
            httpStatusCode = response.code(),
            errorBody = response.errorBody()?.string()
        )
    }catch(e: Exception){
        return APICallResult(
            status = LoadingStatus.ERROR,
            exception = e
        )
    }
}