package com.fastorassignment.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.fastorassignment.data.Constants.TAG

fun log(message: String) {
    Log.d(TAG, message)
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}