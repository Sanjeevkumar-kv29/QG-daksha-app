package com.internshala.booky.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val u_id: Int = 0,
    @ColumnInfo(name = "u_name") val Name: String,
    @ColumnInfo(name = "u_phone") val Phone: String,
    @ColumnInfo(name = "u_access_token") val AccessToken: String
)
