package com.lisery.ex08

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lisery.ex08.R
import com.lisery.ex08.databinding.ActivityMain2Binding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
        val info = sharedPreferences.getString("info", null)
        if (info == null) {
            Toast.makeText(this, "没有读取的数据", Toast.LENGTH_SHORT).show()
        } else {
            val itemType = object : TypeToken<List<CallInfo>>() {}.type
            var callInfoList = Gson().fromJson<List<CallInfo>>(info, itemType)
            callInfoList = callInfoList.sortedBy { callInfo -> callInfo.name }
            val adapter = CallInfoAdapter(this, R.layout.row, callInfoList)
            binding.List.adapter = adapter;
        }
    }
}