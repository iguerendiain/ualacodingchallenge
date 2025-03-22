package ignacio.guerendiain.uala.core.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface CityCalls {
    @GET("cities.json")
    suspend fun getCities(): Response<CityAPI>
}

fun buildCityCalls(baseURL: String): CityCalls = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient.Builder().build())
        .build()
        .create(CityCalls::class.java)
