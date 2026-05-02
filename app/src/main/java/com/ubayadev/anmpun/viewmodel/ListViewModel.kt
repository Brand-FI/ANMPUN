package com.ubayadev.anmpun.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.anmpun.model.Habit

class ListViewModel : ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    init {
        initialHabits()
        refresh()
    }

    fun initialHabits(){
        val habit1 = Habit("Drink Water", "Stay hydrated", 3, 8, "glasses", "💧")
        val habit2 = Habit("Jogging", "Morning run 5km", 1, 1, "session", "🏃‍")
        val habit3 = Habit("Read Book", "Read 10 pages", 0, 10, "pages", "📚")

        habitsLD.value = arrayListOf(habit1, habit2, habit3)
    }

    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false

        val currentList = habitsLD.value ?: arrayListOf()
        habitsLD.value = currentList

        habitLoadErrorLD.value = false
        loadingLD.value = false
    }

    fun add(newHabit: Habit){
        val currentList = habitsLD.value ?: arrayListOf()
        currentList.add(newHabit)
        habitsLD.value = currentList
    }
}
