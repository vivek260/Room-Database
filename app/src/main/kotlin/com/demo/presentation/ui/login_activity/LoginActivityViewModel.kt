package com.demo.presentation.ui.login_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.data.repository.UserRepository
import com.demo.db.entity.UserDetails
import com.demo.presentation.DatabaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivityViewModel: ViewModel()  {
    private var userRepository: UserRepository? = null
    var isUserAvailable = MutableLiveData<Boolean>()

    init {
        userRepository = DatabaseApplication.getDatabaseRepository()
    }

    fun getUserSpecificData(userName:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val data:List<UserDetails>? = userRepository?.getMatchingData(userName,password)
            withContext(Dispatchers.Main) {
                isUserAvailable.value = data?.isNotEmpty()
            }
        }
    }



}