package com.lisery.ex05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex05.R
import com.lisery.ex05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Btn1.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.InfoFragment, Fragment1())
            transaction.commit()
        }

        binding.Btn2.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.InfoFragment, Fragment2())
            transaction.commit()
        }

        binding.Btn3.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.InfoFragment, Fragment3())
            transaction.commit()
        }
    }
}