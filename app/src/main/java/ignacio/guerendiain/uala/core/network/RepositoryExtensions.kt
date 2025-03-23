package ignacio.guerendiain.uala.core.network

import ignacio.guerendiain.uala.core.util.LoadingStatus
import retrofit2.Response

interface NetworkRepository

suspend fun<T> NetworkRepository.runCall(
    call: suspend () -> Response<T>
): Pair<APICallResult, T?> {
    try {
        val response = call()

        return if (response.isSuccessful) Pair(APICallResult(
            status = LoadingStatus.SUCCESS,
            httpStatusCode = response.code()
        ), response.body()) else Pair(APICallResult(
            status = LoadingStatus.ERROR,
            httpStatusCode = response.code(),
            errorBody = response.errorBody()?.string()
        ), null)
    }catch(e: Exception){
        return Pair(APICallResult(
            status = LoadingStatus.ERROR,
            exception = e
        ), null)
    }
}