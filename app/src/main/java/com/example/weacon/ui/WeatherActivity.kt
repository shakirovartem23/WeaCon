package com.example.weacon.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.weacon.R
import com.example.weacon.Request.loadWeather
import com.example.weacon.viewPager.ViewPageAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class WeatherActivity : AppCompatActivity() {
    companion object{
        const val TAG: String = "WEATHER_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val pager = findViewById<ViewPager2>(R.id.viewPager)
        pager.adapter = ViewPageAdapter(listOf(R.drawable._db5b2ce7ba03746b6b68d210941ddbd, R.drawable.free_icon_minus_sign_of_a_line_in_horizontal_position_32320))

        val dots = findViewById<DotsIndicator>(R.id.dots)
        dots.attachTo(pager)

        //StatusBar transparent
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
        window.addFlags(
            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        )
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        GlobalScope.launch(Dispatchers.Main){
            val weather = loadWeather(56.37, 47.53)
        }

    }
}
