package com.demo.presentation.ui.home_activity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.databinding.ActivityHomeBinding
import com.demo.db.entity.UserDetails
import com.demo.presentation.DatabaseApplication
import com.demo.presentation.base.BaseActivity
import com.demo.presentation.model.ViewModelCreator
import com.demo.presentation.ui.home_activity.adapter.UserListAdapter
import com.demo.presentation.utilities.showToast


class HomeActivity : BaseActivity<HomeActivityViewModel, ActivityHomeBinding>() {

    private var adapter: UserListAdapter? = null
    private lateinit var name: String
    private lateinit var mobileNumber: String
    private lateinit var email: String
    private lateinit var password: String
    override fun subscribeToObservers() {
        viewModel.apply {
            readAllData?.observe(this@HomeActivity) {
                Log.d("User List", it.toString())
                val adapter = UserListAdapter(it)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllUsers()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
        binding.addUserBtn.setOnClickListener {
            showAddUserDailog()
        }
    }

    private fun showAddUserDailog() {
        val li = LayoutInflater.from(DatabaseApplication.applicationContext())
        val promptsView: View = li.inflate(R.layout.add_user_dialog, null)
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(
            this@HomeActivity
        )
        alertDialogBuilder.setView(promptsView)
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton(
                "Add",
                DialogInterface.OnClickListener { dialog, id ->
                    val nameEditText =
                        (dialog as AlertDialog).findViewById<View>(com.demo.R.id.etName) as EditText?
                    val mobileEditText =
                        (dialog as AlertDialog).findViewById<View>(com.demo.R.id.etMobileNumber) as EditText?
                    val emailEditText =
                        (dialog as AlertDialog).findViewById<View>(com.demo.R.id.etEmail) as EditText?
                    val passwordEditText =
                        (dialog as AlertDialog).findViewById<View>(com.demo.R.id.etPassword) as EditText?
                    name = nameEditText?.text.toString()
                    mobileNumber = mobileEditText?.text.toString()
                    email = emailEditText?.text.toString()
                    password = passwordEditText?.text.toString()
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
                    }
                })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun createViewModel(): ViewModelCreator<HomeActivityViewModel> = ViewModelCreator(
        HomeActivityViewModel::class.java
    )

    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)

}