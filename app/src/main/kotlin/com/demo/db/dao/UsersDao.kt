package com.demo.db.dao

import androidx.room.*
import com.demo.db.entity.UserDetails

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(usersEntity: UserDetails)

    @Delete
    fun deleteUser(usersEntity: UserDetails)

    @Query("SELECT * FROM user_list_table ORDER BY mobileNumber ASC")
    fun getAllUsers(): List<UserDetails>

    @Query("SELECT * FROM user_list_table WHERE name = :userName AND password = :password")
    fun getMatchingUser(userName:String, password:String): List<UserDetails>
}