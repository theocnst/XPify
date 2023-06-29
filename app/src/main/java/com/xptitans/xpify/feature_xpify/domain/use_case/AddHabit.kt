package com.xptitans.xpify.feature_xpify.domain.use_case

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.repository.HabitRepository

class AddHabit(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(
        habit: Habit
    ) {
        if (habit.name.isBlank()) {
            throw Exception("The name of the habit can't be empty.")
        }
        if (habit.xpAmount.isBlank()) {
            throw Exception("The xp amount of the habit can't be empty.")
        }
        habitRepository.insertHabit(habit)
    }
}