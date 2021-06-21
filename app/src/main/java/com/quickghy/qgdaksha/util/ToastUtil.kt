package com.quickghy.qgdaksha.modules.util

import android.content.Context
import android.widget.Toast

/**
 * @Author: Shubham Rimjha , Sanjeev Kumar
 * @Date: 19-06-2021
 */
fun Context.LongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
