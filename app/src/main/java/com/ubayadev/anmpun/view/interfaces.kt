package com.ubayadev.anmpun.view

import android.view.View
import com.ubayadev.anmpun.model.Habit

interface HabitListListener {
    fun onIncrease(habit: Habit)
    fun onDecrease(habit: Habit)

    fun onClick(v: View, habit:Habit)
}

interface EditHabitListener{
    fun onClick(v: View)
}