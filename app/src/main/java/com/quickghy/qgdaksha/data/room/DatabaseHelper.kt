package com.quickghy.qgdaksha.data.room

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper : SQLiteOpenHelper(Activity() as Context, "database", null, 0) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE tableFav(" +
                    "Id TEXT PRIMARY KEY," +
                    "Name TEXT," +
                    "Phone TEXT," +
                    "AccessToken TEXT," +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}

