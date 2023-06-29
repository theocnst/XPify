package com.xptitans.xpify.feature_xpify.domain.use_case

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.repository.HabitRepository

class GetHabit(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(id: Int): Habit?{
        return habitRepository.getHabitById(id)
    }
}