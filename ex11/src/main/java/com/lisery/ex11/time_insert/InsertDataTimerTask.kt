package com.lisery.ex11.time_insert

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

class InsertDataTimerTask(context: Context) : TimerTask() {
    private val dbHelper: DBHelper = DBHelper(context, null)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun run() {
        dbHelper.insertTime(LocalDateTime.now().toString())
    }
}