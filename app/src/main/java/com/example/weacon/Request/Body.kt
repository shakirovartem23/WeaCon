package com.example.weacon.Request

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherRequest {
    @GET("/v2/forecast/?")
    @Headers("X-YANDEX-API-Key: 035e88d1-2d68-42db-a254-ab4f6c385f0c")
    suspend fun weatherRequest(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("lang") lang: String  = "ru_RU"
    ): WeatherObject
}

data class WeatherObject(
    val now_dt: String,
    val info: Info,
    val fact: Fact,
    val forecast: List<Forecast>
)

data class Info(
    val lat: Double,
    val lon: Double,
    val tsinfo: TZInfo,
)

data class TZInfo(
    val name: String,
    val abbr: String,
)

data class Fact(
    val temp: Int,
    val feels_Like: Int,
    val condition: String,
    val wind_speed: Int,
    val wind_gust: Double,
    val pressure_pa: Int,
    val daytime: String,
    val season: String,
    val cloudness: Int
)

data class Forecast(
    val date: String,
    val sunrise: String,
    val sunset: String,
    val parts: Parts
)

data class Parts(
    val morning: Day,
    val day: Day,
    val evening: Day
)

data class Day(
    val temp_avg: Int,
    val feels_like: Int,
    val condition: String,
    val wind_speed: Double,
    val wind_gust: Double,
    val pressure_pa: Int,
    val cloudness: Int
)