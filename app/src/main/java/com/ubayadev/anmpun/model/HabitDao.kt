package com.ubayadev.anmpun.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habit:Habit)

    @Query("SELECT * FROM habit")
    fun selectAllTodo(): List<Habit>

    @Query("SELECT * FROM habit WHERE uuid= :id")
    fun selectTodo(id:Int): Habit

    @Update
    fun updateHabit(habit: Habit)
    @Delete
    fun deleteTodo(habit: Habit)
}
