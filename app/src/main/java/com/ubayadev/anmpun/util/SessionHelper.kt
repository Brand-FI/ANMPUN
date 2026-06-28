package com.ubayadev.anmpun.util

import android.content.Context
import android.content.SharedPreferences

class SessionHelper(val context: Context) {
    private val shared: SharedPreferences = context.getSharedPreferences("com.ubayadev.anmpun", Context.MODE_PRIVATE)
    fun setLogin(value: Boolean) {
        val editor: SharedPreferences.Editor = shared.edit()
        editor.putBoolean("isLogin", value).apply()
    }

    fun isLogin(): Boolean {
        return shared.getBoolean("isLogin", false)
    }
}