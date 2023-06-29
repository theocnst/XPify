package com.xptitans.xpify.feature_xpify.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xptitans.xpify.feature_xpify.data.data_source.HabitDao
import com.xptitans.xpify.feature_xpify.domain.model.Habit

@Database(
    entities = [Habit::class],
    version = 1
)
abstract class HabitDatabase : RoomDatabase() {

    abstract val dao: HabitDao

    companion object {
        const val DATABASE_NAME = "habit_db"
    }
}