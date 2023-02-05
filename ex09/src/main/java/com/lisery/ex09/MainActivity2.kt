package com.lisery.ex09

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex09.databinding.ActivityMain2Binding
import java.io.File
import java.io.FileInputStream

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Text.text = getData()
    }

    private fun getData(): String {
        val path = applicationContext.filesDir
        val dataDirectory = File(path, "data")
        if (dataDirectory.exists()) {
            val file = File(dataDirectory, "records.txt")
            if (file.exists()) {
                return FileInputStream(file).bufferedReader().use { it.readText() }
            }
        }
        return ""
    }
}