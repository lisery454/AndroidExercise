package com.lisery.ex11.time_insert

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query =
            "CREATE TABLE $TABLE_NAME ( $ID_COL INTEGER PRIMARY KEY, $TIME_COl TEXT )"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun insertTime(time: String) {
        val values = ContentValues()
        values.put(TIME_COl, time)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getTimes(): Cursor? {
        val db = this.readableDatabase
        val query = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        return query
    }

    companion object {
        private const val DATABASE_NAME = "TimeDB"

        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "time_table"

        const val ID_COL = "id"

        const val TIME_COl = "time"
    }
}