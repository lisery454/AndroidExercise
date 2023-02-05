package com.lisery.ex09

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex09.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initChannel("data_produce")
        binding.Btn.setOnClickListener {
            loadData()
        }
    }

    private fun getRandomString(): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..10)
            .map { charset.random() }
            .joinToString("")
    }

    private fun loadData() {
        val runnable = Runnable {
            run {
                runOnUiThread {
                    binding.Btn.isEnabled = false
                    binding.Text.text = "数据等待中"
                }

                val str = getRandomString()
                Thread.sleep(10000)
                saveData(str)

                runOnUiThread {
                    binding.Text.text = "计算已完成"
                    binding.Btn.isEnabled = true
                }

                sendNotification("data_produce")
            }
        }
        Thread(runnable).start()
    }

    private fun saveData(str: String) {
        val path = applicationContext.filesDir
        val dataDirectory = File(path, "data")
        dataDirectory.mkdirs()
        val file = File(dataDirectory, "records.txt")
        FileOutputStream(file).use {
            it.write(str.toByteArray())
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

        val notifyIntent = Intent(this, MainActivity2::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            this, 0, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = Notification.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("数据生成完成")
            .setContentText("点击查看数据")
            .setContentIntent(notifyPendingIntent)
            .build()

        notificationManager.notify(1, notification) // 这里的id不同会导致通知不同，如果一样的话，下一条会顶掉前一条
    }
}