package com.demo.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import androidx.lifecycle.ViewModelProviders
import com.demo.presentation.model.ViewModelCreator

abstract class BaseActivity <M : ViewModel, B : ViewBinding> : AppCompatActivity() {

    lateinit var viewModel: M

    lateinit var binding: B

    companion object {
        var isInForeground = false
    }

    abstract fun subscribeToObservers()

    abstract fun createViewModel(): ViewModelCreator<M>

    abstract fun getViewBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        generateViewModel()
        subscribeToObservers()
    }

    private fun generateViewModel() {
        val creator = createViewModel()
        viewModel = ViewModelProviders.of(this, creator.factory).get(creator.type)
    }

    override fun onResume() {
        super.onResume()
        isInForeground = true
    }
}