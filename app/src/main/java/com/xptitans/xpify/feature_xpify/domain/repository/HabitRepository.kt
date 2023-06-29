package com.xptitans.xpify.feature_xpify.domain.repository

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun insertHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)

    fun getHabits(): Flow<List<Habit>>
    fun getHabitsOrderedByXpAmount(): Flow<List<Habit>>
    fun getHabitsOrderedByName(): Flow<List<Habit>>
    fun getHabitsOrderedByDuration(): Flow<List<Habit>>
}