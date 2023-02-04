package com.lisery.ex04

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class MusicService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private lateinit var player: MediaPlayer
    private lateinit var receiver: MusicReceiver

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI).apply {
            isLooping = true
        }

        receiver = MusicReceiver(player)

        registerReceiver(receiver, IntentFilter().apply { addAction("music") })

        return START_STICKY
    }

    override fun onDestroy() {
        player.stop()
        unregisterReceiver(receiver)
        super.onDestroy()
    }


    class MusicReceiver(private val player: MediaPlayer) : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null)
                if (intent.action == "music") {
                    if (intent.getBooleanExtra("isPlay", false))
                        player.start()
                    else player.pause()
                }
        }
    }
}