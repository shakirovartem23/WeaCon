package com.example.weacon.Alarm_Manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.weacon.R
import com.example.weacon.ui.WeatherActivity

class AlarmReceiver: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotify(
        context: Context,
        intent: Intent
    ){
        val name = "Weather notification"
        val descriptionText = "Weather changes"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("notifyForecasts", name, importance).apply {
            description = descriptionText
        }
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500)


        val builder = NotificationCompat.Builder(context, "notifyForecasts")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("The weather has changed")
            .setContentText("Weather changes. Just take a look ...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVibrate(longArrayOf(100, 200, 300, 400, 500))
            .setAutoCancel(false)
            .setContentIntent(
                PendingIntent
                .getActivity(context, 0, intent,
                PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            )

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(0, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        createNotify(context!!, Intent(context, WeatherActivity::class.java))
    }
}