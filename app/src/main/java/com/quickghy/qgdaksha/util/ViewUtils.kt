package com.mvvm.loginmvvm.util

import android.content.Context
import android.widget.Toast

/**
 * @Author: Shubham Rimjha
 * @Date: 19-06-2021
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}