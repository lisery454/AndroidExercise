package com.lisery.ex01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex01.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val isRemember = intent.getBooleanExtra("isRemember", true)

        binding.TextUserName.text = username
        binding.TextPassword.text = password;
        binding.TextRemember.text = if (isRemember) "是" else "否"

        binding.BackBtn.setOnClickListener {
            finish()
        }
    }
}