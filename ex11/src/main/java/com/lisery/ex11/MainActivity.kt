package com.lisery.ex11

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lisery.ex11.databinding.ActivityMainBinding
import com.lisery.ex11.time_insert.DBHelper
import com.lisery.ex11.time_insert.DataInsertService
import com.lisery.ex11.time_insert.TimeInfo

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startService(Intent(this@MainActivity, DataInsertService::class.java))
        }

        binding.endBtn.setOnClickListener {
            stopService(Intent(this@MainActivity, DataInsertService::class.java))
        }

        binding.showBtn.setOnClickListener {
            val dbHelper = DBHelper(this, null)
            val timeInfoList: MutableList<TimeInfo> = mutableListOf()
            dbHelper.getTimes()?.use {
                while (it.moveToNext()) {
                    timeInfoList.add(
                        TimeInfo(
                            it.getInt(it.getColumnIndex(DBHelper.ID_COL)),
                            it.getString(it.getColumnIndex(DBHelper.TIME_COl))
                        )
                    )
                }
            }


            binding.items.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                if (itemDecorationCount == 0)
                    addItemDecoration(
                        DividerItemDecoration(
                            this@MainActivity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                adapter = TimeInfoAdapter(layoutInflater).apply { submitList(timeInfoList) }
            }
        }
    }
}