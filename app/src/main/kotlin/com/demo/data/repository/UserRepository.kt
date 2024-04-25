package com.demo.data.repository

import com.demo.db.dao.UsersDao
import com.demo.db.entity.UserDetails

class UserRepository(private val usersDao: UsersDao) {
    fun getAllUserList():List<UserDetails>{
        return usersDao.getAllUsers()
    }
    fun addUser(user: UserDetails){
        usersDao.addUser(user)
    }

    fun getMatchingData(userName:String,password:String):List<UserDetails>{
        return usersDao.getMatchingUser(userName,password)
    }
}