package com.lisery.ex03

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lisery.ex03.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ReadBtn.setOnClickListener {
            if (!canReadContacts()) askPermission()

            if (canReadContacts()) showContacts()
        }
    }


    private fun showContacts() {
        val contactsList = getCallList(this)

        binding.items.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            if (itemDecorationCount == 0)
                addItemDecoration(
                    DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
                )
            adapter = CallAdapter(layoutInflater).apply {
                submitList(contactsList)
            }
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
                if (grantResults.isNotEmpty() && grantResults[0] === PackageManager.PERMISSION_GRANTED
                ) {
                    showContacts()
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