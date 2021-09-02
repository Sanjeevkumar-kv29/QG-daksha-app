package com.quickghy.qgdaksha.data.room

import androidx.room.*

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