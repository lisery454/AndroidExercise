package com.lisery.ex10

import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadAsyncTask(val progressBar: ProgressBar, val saveFile: File) :
    AsyncTask<String, Int, String>() {
    override fun doInBackground(vararg url: String?): String {
        var result: String
        Log.d("silly", "ok")

        var conn: HttpURLConnection? = null
        var input: InputStream? = null
        var output: OutputStream? = null
        try {
            conn = URL(url.first()).openConnection() as HttpURLConnection
            conn.connect()
            val fileLength: Int = conn.contentLength
            input = conn.inputStream
            output = FileOutputStream(saveFile)

            val data = ByteArray(4096)
            var total: Long = 0
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                total += count.toLong()

                if (fileLength > 0)
                    publishProgress((total * 100 / fileLength).toInt())

                output.write(data, 0, count)
            }

            result = "success"
        } catch (e: Exception) {
            e.printStackTrace()
            result = "fail"
        } finally {
            input?.close()
            output?.close()
            conn?.disconnect()
        }

        return result
    }

    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.progress = 0
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result == "success")
            progressBar.progress = 100
        else progressBar.progress = 0
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        if (values.isNotEmpty()) progressBar.progress = values.first()!!
    }
}