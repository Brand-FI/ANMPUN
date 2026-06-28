package com.ubayadev.anmpun.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.ubayadev.anmpun.util.SessionHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) :
    AndroidViewModel(application), CoroutineScope {

    val loginResult = MutableLiveData<Boolean>()
    val sessionHelper = SessionHelper(application)
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun isLogin() = sessionHelper.isLogin()
    fun login(username: String, password: String) {
        launch {
            val db = buildDb(getApplication())
            val user = db.userDao().login(username, password)
            if (user != null) {
                sessionHelper.setLogin(true)
            }
            loginResult.postValue(user != null)
        }
    }

}