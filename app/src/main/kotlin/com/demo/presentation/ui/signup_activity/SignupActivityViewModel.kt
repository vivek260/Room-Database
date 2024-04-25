package com.demo.presentation.ui.signup_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.data.repository.UserRepository
import com.demo.db.entity.UserDetails
import com.demo.presentation.DatabaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupActivityViewModel : ViewModel() {
    private var userRepository: UserRepository? = null

    init {
        userRepository = DatabaseApplication.getDatabaseRepository()
    }

    fun addUser(user: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository?.addUser(user)
        }
    }
}