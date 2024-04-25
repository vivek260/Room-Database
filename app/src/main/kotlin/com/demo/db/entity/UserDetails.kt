package com.demo.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_list_table")
data class UserDetails(
    @PrimaryKey()
    val mobileNumber: String,
    val name: String?,
    val email: String?,
    val password: String?,
)