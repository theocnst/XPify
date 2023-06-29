package com.xptitans.xpify.feature_xpify.domain.use_case

import com.xptitans.xpify.feature_xpify.domain.repository.HabitRepository

class RefreshHabits(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke() {
        habitRepository.refreshHabits()
    }
}