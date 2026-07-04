package com.ubayadev.anmpun.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadev.anmpun.model.Habit
import com.ubayadev.anmpun.util.FileHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewHabitViewModel(application: Application) :
    AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val habitLD = MutableLiveData<Habit>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun add(newHabit: Habit) {
        launch {
            val db = buildDb(getApplication())
            db.habitDao().insertAll(newHabit)
        }
    }

    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            habitLD.postValue(db.habitDao().selectTodo(uuid))
        }
    }

    fun update(habit: Habit) {
        launch {
            val db = buildDb(getApplication())
            db.habitDao().updateHabit(habit)
        }
    }
}