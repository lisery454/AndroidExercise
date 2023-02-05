package com.lisery.ex10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lisery.ex10.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.downloadBtn.setOnClickListener {
            val file = File(applicationContext.filesDir, "mongodb.tgz")
            DownloadAsyncTask(binding.progressBar, file)
                .execute("http://115.29.231.93:8080/compare/mongodb.tgz")
        }
    }
}