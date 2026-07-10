package com.ubayadev.anmpun.model

import DB_NAME
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Habit::class, User::class), version = 1)

abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        private var instance: HabitDatabase? = null
        private val LOCK = Any()
        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    instance?.habitDao()?.insertAll(
                        Habit(
                            name = "Drink Water",
                            description = "Stay hydrated",
                            currentProgress = 3,
                            goal = 8,
                            unit = "glasses",
                            iconUrl = "💧"
                        ),
                        Habit(
                            name = "Jogging",
                            description = "Morning run 5km",
                            currentProgress = 1,
                            goal = 1,
                            unit = "session",
                            iconUrl = "🏃‍♀️"
                        ),
                        Habit(
                            name = "Read Book",
                            description = "Read 10 pages",
                            currentProgress = 0,
                            goal = 10,
                            unit = "pages",
                            iconUrl = "📚"
                        )
                    )
                    instance?.userDao()?.insert(
                        User(
                            username = "student",
                            password = "123"
                        )
                    )
                }
            }
        }

        fun buildDatabase(context: Context): HabitDatabase {
            val db = Room.databaseBuilder(
                context.applicationContext,
                HabitDatabase::class.java,
                DB_NAME
            ).addCallback(roomCallback).build()
            instance = db
            return db
        }

        operator fun invoke(context: Context): HabitDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context)
            }
        }
    }
}

