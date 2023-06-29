package com.xptitans.xpify.feature_xpify.domain.use_case

data class HabitUseCases(
    val getHabits: GetHabits,
    val deleteHabit: DeleteHabit,
    val addHabit: AddHabit,
    val getHabit: GetHabit,
    val refreshHabitsFromAPI: RefreshHabits
)
