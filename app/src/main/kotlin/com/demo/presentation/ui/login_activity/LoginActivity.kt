package com.demo.presentation.ui.login_activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.demo.presentation.ui.home_activity.HomeActivity
import com.demo.databinding.ActivityLoginBinding
import com.demo.presentation.base.BaseActivity
import com.demo.presentation.model.ViewModelCreator
import com.demo.presentation.ui.signup_activity.SignupActivity
import com.demo.presentation.utilities.showToast

class LoginActivity : BaseActivity<LoginActivityViewModel, ActivityLoginBinding>() {
    private var userName: String? = null
    private var password: String? = null
    override fun subscribeToObservers() {
        viewModel.apply {
            isUserAvailable?.observe(this@LoginActivity) {
                Log.d("User List", it.toString())
                if(it){
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                    val myEdit = sharedPreferences.edit()
                    myEdit.putBoolean("isLoggedIn", true)
                    myEdit.apply()
                    startActivity(intent)
                finish()
                }else{
                    showToast(this@LoginActivity,"Please enter valid User Name and Password")
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvSignupText.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.etUserName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userName = s.toString()
            }
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                password = s.toString()
            }
        })
        binding.btnLogin.setOnClickListener {
            if(userName != null && userName != "" && password != null && password !="") {
                userName?.let { it1 -> password?.let { it2 ->
                    viewModel.getUserSpecificData(it1,
                        it2
                    )
                } }
            }else {
                showToast(this,"Please enter valid User Name and Password")
            }
        }
    }

    override fun createViewModel(): ViewModelCreator<LoginActivityViewModel> = ViewModelCreator(
        LoginActivityViewModel::class.java
    )

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

}