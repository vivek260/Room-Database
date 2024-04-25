package com.demo.presentation.ui.home_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.data.repository.UserRepository
import com.demo.db.entity.UserDetails
import com.demo.presentation.DatabaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivityViewModel: ViewModel()  {
    private var userRepository: UserRepository? = null
    var readAllData = MutableLiveData<List<UserDetails>>()

    init {
        userRepository = DatabaseApplication.getDatabaseRepository()
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = userRepository?.getAllUserList()
            withContext(Dispatchers.Main) {
                readAllData?.value = data
            }
        }
    }

    fun addUser(user: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository?.addUser(user)
            getAllUsers()
        }
    }
}