package com.xptitans.xpify.feature_xpify.presentation.habit

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.util.HabitOrder
import com.xptitans.xpify.feature_xpify.domain.util.OrderType

data class HabitsState (

    val habits: List<Habit> = emptyList(),
    val habitOrder: HabitOrder = HabitOrder.Name(OrderType.Ascending),
    val isHabitOrderSectionVisible: Boolean = false,

)

