package com.xptitans.xpify.feature_xpify.domain.repository

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    fun getHabits(): Flow<List<Habit>>

    suspend fun insertHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun getHabitById(id: Int): Habit?
}