package com.example.sportapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.sportapp.MainActivity
import com.example.sportapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.text.toInt as toInt

class NotificationMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        Log.d(TAG, "From: ${remoteMessage.from}")

        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            remoteMessage.data[TIME]?.let { it1 -> sendNotification(null, it1, remoteMessage.data[MEASURE]) }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")

    }

    private fun sendNotification(name: String?, time: String, measure: String?) {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        var titleName = name
        if (titleName == null) {
            titleName = "БАД"
        }

        val timeFormat = convertTimeToHHMMFormat(time)

        val channelId = getString(R.string.default_notification_channel_id)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Время принимать $titleName")
            .setContentText("Время принимать дозировку $measure в $timeFormat")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(1337, notificationBuilder.build())
    }

    private fun convertTimeToHHMMFormat(time: String): String {

        val minutes = time.toInt()

        // Проверяем, что минуты находятся в допустимом диапазоне
        require(minutes in 0..1440) { "Minutes should be in the range of 0 to 1440" }

        // Вычисляем часы и оставшиеся минуты
        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        // Форматируем часы и минуты в строку "hh:mm"
        return String.format("%02d:%02d", hours, remainingMinutes)
    }

    companion object {
        private const val TAG = "NotificationMessagingService"
        private const val TIME = "time"
        private const val MEASURE = "measure"
    }

}