package com.demo.presentation.ui.signup_activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.demo.databinding.ActivitySignupBinding
import com.demo.db.entity.UserDetails
import com.demo.presentation.base.BaseActivity
import com.demo.presentation.model.ViewModelCreator
import com.demo.presentation.ui.login_activity.LoginActivity
import com.demo.presentation.utilities.showToast

class SignupActivity : BaseActivity<SignupActivityViewModel, ActivitySignupBinding>() {
    private lateinit var name: String
    private lateinit var mobileNumber: String
    private lateinit var email: String
    private lateinit var password: String
    override fun subscribeToObservers() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListener()
        binding.btnSignup.setOnClickListener {
            if (name.isEmpty()) {
                showToast(this, "Please Enter Your Name")
            } else if (mobileNumber.length != 10) {
                showToast(this, "Please Enter Valid Mobile Number")
            } else if (email.isEmpty()) {
                showToast(this, "Please Enter Your Email ID")
            } else if (password.length < 8) {
                showToast(this, "Please Enter Password which contains minimum 8 characters")
            } else {
                viewModel.addUser(UserDetails(mobileNumber,name, email, password))
                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun setupListener() {
        binding.tvSignupText.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name = s.toString()
            }
        })
        binding.etMobileNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mobileNumber = s.toString()
            }

        })
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                email = s.toString()
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


    }

    override fun createViewModel(): ViewModelCreator<SignupActivityViewModel> = ViewModelCreator(
        SignupActivityViewModel::class.java
    )

    override fun getViewBinding(): ActivitySignupBinding =
        ActivitySignupBinding.inflate(layoutInflater)

}