package com.ubayadev.anmpun.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadev.anmpun.model.Habit
import com.ubayadev.anmpun.util.FileHelper

class ListViewModel(application: Application):
    AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun initialHabits(): ArrayList<Habit> {

        val list = arrayListOf(
            Habit("Drink Water", "Stay hydrated", 3, 8, "glasses", "💧"),
            Habit("Jogging", "Morning run 5km", 1, 1, "session", "🏃‍"),
            Habit("Read Book", "Read 10 pages", 0, 10, "pages", "📚")
        )

        val jsonString = Gson().toJson(list)
        val fileHelper = FileHelper(getApplication())
        fileHelper.writeToFile(jsonString)
        return list
    }
    fun refresh() {

        loadingLD.value = true
        habitLoadErrorLD.value = false

        val fileHelper = FileHelper(getApplication())
        val sType = object : TypeToken<List<Habit>>(){}.type

        val result: List<Habit> = Gson().fromJson(fileHelper.readFromFile(), sType)
        if(result.isNullOrEmpty()) {
            habitsLD.value = initialHabits()
        } else {
            habitsLD.value = result as ArrayList<Habit>
        }
        loadingLD.value = false
    }

    fun increaseProgress(position: Int) {
        habitsLD.value?.let {
            if(it[position].currentProgress < it[position].goal) {
                it[position].currentProgress++
                saveToFile()
            }
        }
    }

    fun decreaseProgress(position: Int) {
        habitsLD.value?.let {
            if(it[position].currentProgress > 0) {
                it[position].currentProgress--
                saveToFile()
            }
        }
    }

    fun saveToFile() {
        val jsonString = Gson().toJson(habitsLD.value)
        val fileHelper = FileHelper(getApplication())
        fileHelper.writeToFile(jsonString)
    }

}
