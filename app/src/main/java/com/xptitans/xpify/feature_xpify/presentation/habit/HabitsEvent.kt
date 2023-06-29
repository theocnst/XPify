package com.xptitans.xpify.feature_xpify.presentation.habit

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.util.HabitOrder

sealed class HabitsEvent{

    data class Order(val habitOrder: HabitOrder) : HabitsEvent ()
    data class DeleteHabit(val habit: Habit) : HabitsEvent ()

    object RestoreHabit : HabitsEvent()
    object ToggleOrderSection : HabitsEvent()
    object RefreshHabits : HabitsEvent()
}