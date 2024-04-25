package com.demo.presentation.ui.launcher_activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.demo.databinding.ActivityLauncherBinding
import com.demo.presentation.base.BaseActivity
import com.demo.presentation.model.ViewModelCreator
import com.demo.presentation.ui.home_activity.HomeActivity
import com.demo.presentation.ui.login_activity.LoginActivity
import com.demo.presentation.utilities.makeVisible
import java.util.*


class LauncherActivity : BaseActivity<LauncherActivityViewModel, ActivityLauncherBinding>() {
    private var isAnimationFinished: Boolean = false


    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val zoomIn = ScaleAnimation(
            0f,
            1f,
            0f,
            1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        zoomIn.duration = 2500
        zoomIn.fillAfter = true
        binding.logo.startAnimation(zoomIn)
        zoomIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) = Unit
            override fun onAnimationEnd(p0: Animation?) {
                isAnimationFinished = true
                launchScreen()
            }

            override fun onAnimationRepeat(p0: Animation?) = Unit
        })
    }

    private fun launchScreen(){
        binding.tvTagLine.makeVisible()
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val sharedPref = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                val isLoggedIn = sharedPref.getBoolean("isLoggedIn",false)
                if(isLoggedIn){
                    launchHomeScreen()
                }else{
                    launchLoginScreen()
                }
            }
        }, 1500)
    }

    private fun launchLoginScreen() {
        val intent = Intent(this@LauncherActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun launchHomeScreen() {
        val intent = Intent(this@LauncherActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun subscribeToObservers() {

    }

    override fun createViewModel(): ViewModelCreator<LauncherActivityViewModel> =  ViewModelCreator(
        LauncherActivityViewModel::class.java)

    override fun getViewBinding(): ActivityLauncherBinding = ActivityLauncherBinding.inflate(layoutInflater)


}