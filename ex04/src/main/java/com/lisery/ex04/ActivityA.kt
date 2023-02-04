package com.lisery.ex04

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex04.databinding.ActivityABinding

class ActivityA : AppCompatActivity() {

    private lateinit var binding: ActivityABinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityABinding.inflate(layoutInflater)
        setContentView(binding.root)

        startService(Intent(this@ActivityA, MusicService::class.java))

        binding.PlayBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("isPlay", true)
            intent.action = "music"
            sendBroadcast(intent)
        }
        binding.StopBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("isPlay", false)
            intent.action = "music"
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        stopService(Intent(this@ActivityA, MusicService::class.java))
        super.onDestroy()
    }
}