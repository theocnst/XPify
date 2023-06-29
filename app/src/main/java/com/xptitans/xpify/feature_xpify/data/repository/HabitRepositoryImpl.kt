package com.xptitans.xpify.feature_xpify.data.repository

import com.xptitans.xpify.feature_xpify.data.data_source.HabitDao
import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow


class HabitRepositoryImpl(
    private val dao: HabitDao
): HabitRepository {
    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        dao.deleteHabit(habit)
    }

    override fun getHabits(): Flow<List<Habit>> {
        return dao.getHabits()
    }

    override fun getHabitsOrderedByXpAmount(): Flow<List<Habit>> {
        return dao.getHabitsOrderedByXpAmount()
    }

    override fun getHabitsOrderedByName(): Flow<List<Habit>> {
        return dao.getHabitsOrderedByName()
    }

    override fun getHabitsOrderedByDuration(): Flow<List<Habit>> {
        return dao.getHabitsOrderedByDuration()
    }
}