package com.example.weacon.Request

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.weather.yandex.ru"
var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
var client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .addInterceptor {
        val request = it.request().newBuilder().build()
        it.proceed(request)
    }
    .build()
val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object GitApi {
    val retrofitService: WeatherRequest by lazy { retrofit.create(
        WeatherRequest::class.java) }
}