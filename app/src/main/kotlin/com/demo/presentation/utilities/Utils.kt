package com.demo.presentation.utilities

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun showToast(context: Context, msg: String){
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
    }
