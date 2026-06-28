package com.ubayadev.anmpun.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadev.anmpun.model.Habit
import com.ubayadev.anmpun.model.HabitDatabase
import com.ubayadev.anmpun.util.FileHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application) :
    AndroidViewModel(application), CoroutineScope {
    val habitsLD = MutableLiveData<List<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            habitsLD.postValue(db.habitDao().selectAllTodo())
            loadingLD.postValue(false)
        }
    }


    fun increaseProgress(habit: Habit) {
        if (habit.currentProgress < habit.goal) {
            habit.currentProgress++
            launch {
                val db = buildDb(getApplication())
                db.habitDao().updateHabit(habit)
                habitsLD.postValue(db.habitDao().selectAllTodo())
            }
        }
    }
    fun decreaseProgress(habit: Habit) {
        if (habit.currentProgress > 0) {
            habit.currentProgress--
            launch {
                val db = buildDb(getApplication())
                db.habitDao().updateHabit(habit)
                habitsLD.postValue(
                    db.habitDao().selectAllTodo()
                )
            }
        }
    }
}
