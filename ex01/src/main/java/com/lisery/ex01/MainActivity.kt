package com.lisery.ex01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.LoginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("username", binding.TextUserName.text.toString())
            intent.putExtra("password", binding.TextPassword.text.toString())
            intent.putExtra("isRemember", binding.IsRemember.isChecked)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("silly", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("silly","onResume()")
    }

    override fun onPause() {
        Log.d("silly","onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d("silly","onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("silly","onDestroy()")
        super.onDestroy()
    }

}