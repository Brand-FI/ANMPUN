package com.ubayadev.anmpun.model

data class Habit(
    val name: String,
    val description: String,
    var currentProgress: Int,
    val goal: Int,
    val unit: String,
    val iconUrl: String? = null
)