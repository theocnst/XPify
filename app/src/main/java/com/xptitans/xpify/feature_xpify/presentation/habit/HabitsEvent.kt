package com.xptitans.xpify.feature_xpify.presentation.habit

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.util.HabitOrder

sealed class HabitsEvent{

    data class Order(val habitOrder: HabitOrder) : HabitsEvent ()
    data class DeleteHabit(val habit: Habit) : HabitsEvent ()

    object RestoreHabit : HabitsEvent()
    object ToggleOrderSection : HabitsEvent()

//    object AddHabits : HabitsEvent
//
//    data class SetName(val name: String) : HabitsEvent
//    data class SetXpAmount(val xpAmount: Int) : HabitsEvent
//    data class SetDuration(val duration: Int) : HabitsEvent
//
//    object ShowDialog : HabitsEvent
//    object HideDialog : HabitsEvent
//
//    data class SortHabits(val sortType: SortType) : HabitsEvent
//    data class DeleteHabits(val habit: Habit) : HabitsEvent
}