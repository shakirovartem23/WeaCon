package com.example.weacon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weacon.R
import com.example.weacon.Request.loadWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Default) {
            println(loadWeather(40, 50))
        }
    }
}