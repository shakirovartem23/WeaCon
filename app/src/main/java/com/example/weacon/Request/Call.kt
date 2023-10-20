package com.example.weacon.Request

suspend fun loadWeather(lat: Int, lon: Int): WeatherObject? {
    try {
        return GitApi.retrofitService.weatherRequest(lat.toString(), lon.toString())
    } catch(e: retrofit2.HttpException) {
        return null
        e.run { printStackTrace() }
    }
}