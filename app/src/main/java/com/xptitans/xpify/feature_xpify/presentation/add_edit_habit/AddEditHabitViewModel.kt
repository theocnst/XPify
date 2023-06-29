package com.xptitans.xpify.feature_xpify.presentation.add_edit_habit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.use_case.HabitUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditHabitViewModel @Inject constructor(
    private val habitUseCases: HabitUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _habitName = mutableStateOf(
        HabitTextFieldState(
            hint = "Enter habit name..."
        )
    )
    val habitName: State<HabitTextFieldState> = _habitName

    private val _habitXpAmount = mutableStateOf(
        HabitTextFieldState(
            hint = "Enter XP amount..."
        )
    )
    val habitXpAmount: State<HabitTextFieldState> = _habitXpAmount

    private val _habitColor = mutableStateOf(Habit.habitColors.random().toArgb())
    val habitColor: State<Int> = _habitColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentHabitId: Int? = null

    init {
        savedStateHandle.get<Int>("habitId")?.let { habitId ->
            if (habitId != -1) {
                viewModelScope.launch {
                    habitUseCases.getHabit(habitId)?.also { habit ->
                        currentHabitId = habit.id
                        _habitName.value = habitName.value.copy(
                            text = habit.name,
                            isHintVisible = false
                        )
                        _habitXpAmount.value = habitXpAmount.value.copy(
                            text = habit.xpAmount,
                            isHintVisible = false
                        )
                        _habitColor.value = habit.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditHabitEvent) {
        when (event) {
            is AddEditHabitEvent.EnteredName -> {
                _habitName.value = habitName.value.copy(
                    text = event.value
                )
            }

            is AddEditHabitEvent.ChangeNameFocus -> {
                _habitName.value = habitName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            habitName.value.text.isBlank()
                )
            }

            is AddEditHabitEvent.EnteredXpAmount -> {
                _habitXpAmount.value = _habitXpAmount.value.copy(
                    text = event.value
                )
            }

            is AddEditHabitEvent.ChangeXpAmountFocus -> {
                _habitXpAmount.value = _habitXpAmount.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _habitXpAmount.value.text.isBlank()
                )
            }

            is AddEditHabitEvent.ChangeColor -> {
                _habitColor.value = event.color
            }

            is AddEditHabitEvent.SaveHabit -> {
                viewModelScope.launch {
                    try {
                        habitUseCases.addHabit(
                            Habit(
                                name = habitName.value.text,
                                xpAmount = habitXpAmount.value.text,
                                color = habitColor.value,
                                id = currentHabitId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveHabit)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveHabit : UiEvent()
    }
}