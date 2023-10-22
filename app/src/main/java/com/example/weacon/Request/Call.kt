package com.example.weacon.Request

suspend fun loadWeather(lat: Double, lon: Double): WeatherObject? {
    try {
        return GitApi.retrofitService.weatherRequest(lat.toString(), lon.toString())
    } catch(e: retrofit2.HttpException) {
        return null
        e.run { printStackTrace() }
    }
}