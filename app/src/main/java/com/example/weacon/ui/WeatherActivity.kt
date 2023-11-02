package com.example.weacon.ui

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.weacon.R
import com.example.weacon.Request.loadWeather
import com.example.weacon.viewPager.ViewPageAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.droidsonroids.gif.GifImageView


fun hasConnection(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    if (wifiInfo != null && wifiInfo.isConnected) {
        return true
    }
    wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    if (wifiInfo != null && wifiInfo.isConnected) {
        return true
    }
    wifiInfo = cm.activeNetworkInfo
    return wifiInfo != null && wifiInfo.isConnected
}
fun isProgress(now: String, start: String, end: String): Int{
    val start = start.substring(0..1).toInt()*60+start.substring(3..4).toInt()
    val end = end.substring(0..1).toInt()*60+end.substring(3..4).toInt()
    val now = now.substring(0..1).toInt()*60+now.substring(3..4).toInt()
    val all = (end-start).toDouble()
    return ((now-start)/(all/100)).toInt()
}
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
                .shuffled()
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

        if(hasConnection(this@WeatherActivity)) {
            GlobalScope.launch(Dispatchers.Main) {
                val weather = loadWeather(56.37, 47.53)
                Log.d(TAG, weather.toString())

                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                val isProgress = isProgress(
                    (weather!!
                        .now_dt
                        .substring(11..12)
                        .toInt()+3)
                        .toString()+
                            weather!!
                                .now_dt
                                .substring(13..14)+
                            weather!!
                                .now_dt
                                .substring(15..16),
                    weather.forecasts.first().sunrise,
                    weather.forecasts.first().sunset
                )
                progressBar.progress = isProgress

                val gifImage = findViewById<GifImageView>(R.id.gifImageView)
                val conditionNow = weather.fact.condition
                val conViewMod = mapOf(
                    "clear" to R.drawable.giphy__1_,
                    "partly-cloudy" to R.drawable.giphy__2_,
                    "cloudy" to R.drawable.giphy__3_,
                    "overcast" to R.drawable.giphy__3_,
                    "light-rain" to R.drawable.giphy__5_,
                    "rain" to R.drawable.giphy__5_,
                    "heavy-rain" to R.drawable.giphy__6_,
                    "showers" to R.drawable.giphy__7_,
                    "wet-snow" to R.drawable.giphy__8_,
                    "light-snow" to R.drawable.giphy__8_,
                    "snow" to R.drawable.giphy__9_,
                    "snow-showers" to R.drawable.giphy__9_,
                    "hail" to R.drawable._db5b2ce7ba03746b6b68d210941ddbd,
                    "thunderstorm" to R.drawable._db5b2ce7ba03746b6b68d210941ddbd,
                    "thunderstorm-with-rain" to R.drawable._db5b2ce7ba03746b6b68d210941ddbd,
                    "thunderstorm-with-hail" to R.drawable._db5b2ce7ba03746b6b68d210941ddbd,
                )

                gifImage.setBackgroundResource(conViewMod[conditionNow]!!)

                //Other
                val textC = findViewById<TextView>(R.id.textC)
                val textFeels = findViewById<TextView>(R.id.feels_like)
                val textOvercast = findViewById<TextView>(R.id.textOvercast)
                val textDate = findViewById<TextView>(R.id.textDate)
                val textSun = findViewById<TextView>(R.id.textSun)

                //Morning
                val textCMorning = findViewById<TextView>(R.id.textCMorning)
                val textConMorning = findViewById<TextView>(R.id.textConMorning)
                val textWindMorning = findViewById<TextView>(R.id.textWindMorning)
                val textPaMorning = findViewById<TextView>(R.id.textPaMorning)
                val textCloudMorning = findViewById<TextView>(R.id.textCloudMorning)

                //Day
                val textCDay = findViewById<TextView>(R.id.textCDay)
                val textConDay = findViewById<TextView>(R.id.textConDay)
                val textWindDay = findViewById<TextView>(R.id.textWindDay)
                val textPaDay = findViewById<TextView>(R.id.textPaDay)
                val textCloudDay = findViewById<TextView>(R.id.textCloudDay)

                //Evening
                val textCEvening = findViewById<TextView>(R.id.textCEvening)
                val textConEvening = findViewById<TextView>(R.id.textConEvening)
                val textWindEvening = findViewById<TextView>(R.id.textWindEvening)
                val textPaEvening = findViewById<TextView>(R.id.textPaEvening)
                val textCloudEvening = findViewById<TextView>(R.id.textCloudEvening)

                if (weather != null) {

                    Log.d(TAG, weather.forecasts.size.toString())

                    //Other
                    textC.text = weather.fact.temp.toString()
                    textFeels.text = weather.fact.feels_like.toString()
                    textOvercast.text = weather.fact.condition
                    textDate.text =
                        (weather.now_dt.substring(8..9) + "." + weather.now_dt.substring(5..6))
                    textSun.text =
                        (weather.forecasts.first().sunrise + " ~ " + weather.forecasts.first().sunset)

                    //Morning
                    textCMorning.text =
                        weather.forecasts.first().parts.morning.temp_avg.toInt().toString()
                    textConMorning.text = weather.forecasts.first().parts.morning.condition
                    textWindMorning.text =
                        weather.forecasts.first().parts.morning.wind_speed.toInt().toString()
                    textPaMorning.text =
                        weather.forecasts.first().parts.morning.pressure_pa.toString()
                    textCloudMorning.text =
                        weather.forecasts.first().parts.morning.cloudness.toInt().toString()

                    //Day
                    textCDay.text = weather.forecasts.first().parts.day.temp_avg.toInt().toString()
                    textConDay.text = weather.forecasts.first().parts.day.condition
                    textWindDay.text =
                        weather.forecasts.first().parts.day.wind_speed.toInt().toString()
                    textPaDay.text = weather.forecasts.first().parts.day.pressure_pa.toString()
                    textCloudDay.text =
                        weather.forecasts.first().parts.day.cloudness.toInt().toString()

                    //Evening
                    textCEvening.text =
                        weather.forecasts.first().parts.evening.temp_avg.toInt().toString()
                    textConEvening.text = weather.forecasts.first().parts.evening.condition
                    textWindEvening.text =
                        weather.forecasts.first().parts.evening.wind_speed.toInt().toString()
                    textPaEvening.text =
                        weather.forecasts.first().parts.evening.pressure_pa.toString()
                    textCloudEvening.text =
                        weather.forecasts.first().parts.evening.cloudness.toInt().toString()

                    val chart = findViewById<LineChart>(R.id.chart)
                    val axisLeft = chart.axisLeft
                    val axisRight = chart.axisRight
                    val xAxis = chart.xAxis
                    val entries = mutableListOf<Entry>()
                    val entries1 = mutableListOf<Entry>()
                    val entries2 = mutableListOf<Entry>()

                    var count = 0

                    for (value in weather.forecasts) {
                        count += 1
                        entries += Entry(count.toFloat(), value.parts.day.temp_avg.toFloat())
                        entries1 += Entry(
                            count.toFloat(),
                            value.parts.day.pressure_pa.toFloat() - 1000
                        )
                        entries2 += Entry(count.toFloat(), value.parts.day.wind_speed.toFloat())
                    }

                    val chartDataSet = LineDataSet(entries, "Temperature, t°C")
                    chartDataSet.axisDependency = YAxis.AxisDependency.LEFT
                    chartDataSet.color = Color.BLACK
                    chartDataSet.gradientColor
                    chartDataSet.circleColors = listOf(Color.LTGRAY)
                    chartDataSet.circleRadius = 5F
                    chartDataSet.lineWidth = 4F
                    chartDataSet.setDrawValues(false)
                    chartDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
                    chartDataSet.cubicIntensity = 0.2F

                    val chartDataSet1 = LineDataSet(entries1, "Pressure, Pa-1000")
                    chartDataSet1.axisDependency = YAxis.AxisDependency.LEFT
                    chartDataSet1.color = Color.RED
                    chartDataSet1.circleColors = listOf(Color.GREEN)
                    chartDataSet1.circleRadius = 5F
                    chartDataSet1.lineWidth = 4F
                    chartDataSet1.setDrawValues(false)
                    chartDataSet1.mode = LineDataSet.Mode.CUBIC_BEZIER
                    chartDataSet1.cubicIntensity = 0.2F

                    val chartDataSet2 = LineDataSet(entries2, "Wind speed, m/s")
                    chartDataSet2.axisDependency = YAxis.AxisDependency.LEFT
                    chartDataSet2.color = Color.BLUE
                    chartDataSet2.circleColors = listOf(Color.YELLOW)
                    chartDataSet2.circleRadius = 5F
                    chartDataSet2.lineWidth = 4F
                    chartDataSet2.setDrawValues(false)
                    chartDataSet2.mode = LineDataSet.Mode.CUBIC_BEZIER
                    chartDataSet2.cubicIntensity = 0.2F


                    val data = LineData(listOf(chartDataSet, chartDataSet1, chartDataSet2))
                    chart.data = data

                    chart.axisRight.setDrawAxisLine(false)
                    axisLeft.isEnabled = false
                    axisLeft.setDrawAxisLine(false)
                    axisRight.setDrawAxisLine(false)
                    axisRight.isEnabled = false
                    xAxis.isEnabled = false
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.granularity = 1F
                    xAxis.labelCount = 0
                    chart.setTouchEnabled(false)
                    chart.legend.isEnabled = false
                    xAxis.setDrawGridLines(false)
                    xAxis.setDrawAxisLine(false)
                    chart.isScaleXEnabled = true
                    chart.description = null

                    val legend = chart.legend
                    legend.form = Legend.LegendForm.CIRCLE
                    legend.isEnabled = true

                    chart.animateXY(8000, 10000)
                    chart.invalidate()
                }
            }
        } else{
            Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_LONG).show()
        }

    }
}
