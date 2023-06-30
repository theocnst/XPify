package com.xptitans.xpify.feature_xpify.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xptitans.xpify.feature_xpify.domain.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT * FROM habit WHERE id = :id")
    suspend fun getHabitById(id: Int): Habit?
    @Query("SELECT * FROM habit")
    fun getHabits(): Flow<List<Habit>>

    @Query("SELECT * FROM habit ORDER BY xpAmount DESC")
    fun getHabitsOrderedByXpAmount(): Flow<List<Habit>>

    @Query("SELECT * FROM habit ORDER BY name ASC")
    fun getHabitsOrderedByName(): Flow<List<Habit>>
}