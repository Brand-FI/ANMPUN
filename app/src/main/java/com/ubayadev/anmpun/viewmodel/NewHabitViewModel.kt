package com.ubayadev.anmpun.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadev.anmpun.model.Habit
import com.ubayadev.anmpun.util.FileHelper

class NewHabitViewModel (application: Application):
    AndroidViewModel(application) {
    fun add(newHabit: Habit){

        val fileHelper = FileHelper(getApplication())
        val sType = object : TypeToken<List<Habit>>() {}.type
        val result = Gson().fromJson<List<Habit>>(fileHelper.readFromFile(), sType)
        val list = result.toMutableList()
        list.add(newHabit)
        val jsonString = Gson().toJson(list)
        fileHelper.writeToFile(jsonString)
    }
}