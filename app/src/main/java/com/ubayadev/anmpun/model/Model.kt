package com.ubayadev.anmpun.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name="username")
    val username: String,
    @ColumnInfo(name="password")
    val password: String
)

@Entity
data class Habit(
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="description")
    val description:String,
    @ColumnInfo(name="currentProgress")
    var currentProgress:Int,
    @ColumnInfo(name="goal")
    val goal:Int,
    @ColumnInfo(name="unit")
    val unit:String,
    @ColumnInfo(name="iconUrl")
    val iconUrl:String? = null
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0

    fun status(): String {
        return if (currentProgress >= goal) {
            "Completed"
        } else {
            "In Progress"
        }
    }
}