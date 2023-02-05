package com.lisery.ex08

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lisery.ex08.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoadBtn.setOnClickListener {
            if (!canReadContacts()) askPermission()
            if (canReadContacts()) loadContacts()
        }

        binding.ShowBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun loadContacts() {
        val contactsList = getCallList(this)

        val toJson = Gson().toJson(contactsList)

        Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show()

        val sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply() {
            putString("info", toJson)
            commit()
        }
    }

    private fun canReadContacts(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    loadContacts()
                } else {
                    Toast.makeText(binding.root.context, "不能读取，除非允许权限！", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun askPermission() {
        requestPermissions(listOf(Manifest.permission.READ_CONTACTS).toTypedArray(), 1)
    }

    @SuppressLint("Range", "Recycle")
    fun getCallList(context: Context?): List<CallInfo> {
        val callList: MutableList<CallInfo> = ArrayList()
        val cursor = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val callInfo = CallInfo(name, number.replace(" ", ""))
                callList.add(callInfo)
            }
        }
        return callList
    }
}