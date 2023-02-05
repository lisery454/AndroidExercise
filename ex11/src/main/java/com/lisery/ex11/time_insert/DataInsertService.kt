package com.lisery.ex11.time_insert

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class DataInsertService : Service() {
    private val timer = Timer(true)
    private val timerTask = InsertDataTimerTask(this)

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer.schedule(timerTask, 0, 2000)
        return START_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
}



