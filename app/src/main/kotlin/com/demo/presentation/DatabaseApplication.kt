package com.demo.presentation

import android.app.Application
import android.content.Context
import com.demo.data.repository.UserRepository
import com.demo.db.AppDatabase
import com.demo.db.dao.UsersDao

class DatabaseApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: DatabaseApplication? = null
        var userRepository:UserRepository?=null
        private var usersDao: UsersDao? = null
        fun applicationContext(): Context {
            return instance?.applicationContext!!
        }
        fun getDatabaseRepository(): UserRepository? {
            return userRepository
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        usersDao = AppDatabase.getDatabase(this).dao()
        userRepository = usersDao?.let { UserRepository(it)}
    }


}