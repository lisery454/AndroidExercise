package com.lisery.ex02

import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex02.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.OpenBtn.setOnClickListener { showInputDialog() }
    }

    private fun showInputDialog() {
        val editText = EditText(this@MainActivity)
        editText.gravity = Gravity.CENTER_HORIZONTAL
        editText.textSize = 20F
        val inputDialog = AlertDialog.Builder(this@MainActivity)
        inputDialog.setTitle("输入").setView(editText)
        inputDialog.setPositiveButton(
            "保存"
        ) { _, _ ->
            binding.NumText.text = editText.text
        }.show()
    }
}