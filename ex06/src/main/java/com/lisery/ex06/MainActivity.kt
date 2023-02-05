package com.lisery.ex06

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.lisery.ex06.R
import com.lisery.ex06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var r = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initChannel("hello")
        binding.Btn.setOnClickListener {
            r++
            sendNotification("hello")
        }
    }

    private fun initChannel(channelID: String) {
        val channel =
            NotificationChannel(channelID, "hello_channel", NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification(channelID: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = Notification.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("hello $r")
            .setContentText("这是一条通知")
            .build()

        notificationManager.notify(r, notification) // 这里的id不同会导致通知不同，如果一样的话，下一条会顶掉前一条
    }
}