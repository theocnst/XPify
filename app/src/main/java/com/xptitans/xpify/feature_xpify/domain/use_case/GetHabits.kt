package com.xptitans.xpify.feature_xpify.domain.use_case

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.repository.HabitRepository
import com.xptitans.xpify.feature_xpify.domain.util.HabitOrder
import com.xptitans.xpify.feature_xpify.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHabits(
    private val repository: HabitRepository
) {
    operator fun invoke(
        habitOrder: HabitOrder = HabitOrder.Name(OrderType.Ascending)
    ): Flow<List<Habit>> {
        return repository.getHabits().map { habits ->
            when (habitOrder.orderType) {
                is OrderType.Ascending -> {
                    when (habitOrder) {
                        is HabitOrder.Name -> habits.sortedBy { it.name.lowercase() }
                        is HabitOrder.XpAmount -> habits.sortedBy { it.xpAmount }
                    }
                }
                is OrderType.Descending -> {
                    when (habitOrder) {
                        is HabitOrder.Name -> habits.sortedByDescending { it.name.lowercase() }
                        is HabitOrder.XpAmount -> habits.sortedByDescending { it.xpAmount }
                    }
                }
            }
        }
    }
}