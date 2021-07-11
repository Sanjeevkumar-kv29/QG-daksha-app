package com.internshala.booky.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.internshala.Usery.database.UserDAO

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {


    abstract fun userDao(): UserDAO

}