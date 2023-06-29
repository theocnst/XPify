package com.xptitans.xpify.feature_xpify.presentation.habit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.use_case.HabitUseCases
import com.xptitans.xpify.feature_xpify.domain.util.HabitOrder
import com.xptitans.xpify.feature_xpify.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitsViewModel @Inject constructor(
    private val habitUseCases: HabitUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HabitsState())
    val state: State<HabitsState> = _state

    private var recentlyDeletedHabit: Habit? = null

    private var getHabitsJob: Job? = null

    init {
        getHabits(HabitOrder.Name(OrderType.Ascending))
    }

    fun onEvent(event: HabitsEvent) {
        when (event) {
            is HabitsEvent.RestoreHabit -> {
                viewModelScope.launch {
                    habitUseCases.addHabit(recentlyDeletedHabit ?: return@launch)
                    recentlyDeletedHabit = null
                }
            }

            is HabitsEvent.DeleteHabit -> {
                viewModelScope.launch {
                    habitUseCases.deleteHabit(event.habit)
                    recentlyDeletedHabit = event.habit
                }
            }

            is HabitsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isHabitOrderSectionVisible = !state.value.isHabitOrderSectionVisible
                )
            }

            is HabitsEvent.Order -> {
                if (state.value.habitOrder::class == event.habitOrder::class
                    && state.value.habitOrder.orderType == event.habitOrder.orderType
                ) {
                    _state.value = state.value.copy(

                    )
                }
            }

            is HabitsEvent.RefreshHabits -> {
                refreshHabits()
            }
        }
    }

    private fun getHabits(habitOrder: HabitOrder) {
        getHabitsJob?.cancel()
        getHabitsJob = habitUseCases.getHabits(habitOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    habits = notes,
                    habitOrder = habitOrder
                )
            }
            .launchIn(viewModelScope)
    }
    private fun refreshHabits() {
        viewModelScope.launch {
            habitUseCases.refreshHabitsFromAPI()
            getHabits(state.value.habitOrder)
        }
    }
}