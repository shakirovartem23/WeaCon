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
        pager.adapter = ViewPageAdapter(
            listOf(
                R.drawable._1,
                R.drawable._2,
                R.drawable._3,
                R.drawable._4,
                R.drawable. _5,
                R.drawable._6,
                R.drawable._7,
                R.drawable._8,
                R.drawable._9,
                R.drawable._10,
                R.drawable._11,
                R.drawable._12,
                R.drawable._13,
                R.drawable._14,
                R.drawable._15,
                R.drawable._16,
                R.drawable._17,
                R.drawable._18,
                R.drawable._19,
                R.drawable._20,
                R.drawable._21,
                R.drawable._22,
                R.drawable._23,
                R.drawable._24,
                R.drawable._25,
                R.drawable._26,
                R.drawable._28,
                R.drawable._29,
                R.drawable._30,
                R.drawable._31,
                R.drawable._32
        )
        )

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
