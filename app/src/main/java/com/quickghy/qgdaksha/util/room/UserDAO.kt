package com.internshala.Usery.database

import androidx.room.*
import com.internshala.booky.database.UserEntity

@Dao
interface UserDAO {

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM token_store WHERE u_id == 0")
    fun getUser(): UserEntity


}