package com.internshala.Usery.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.internshala.booky.database.UserEntity

@Dao
interface UserDAO {

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)


}