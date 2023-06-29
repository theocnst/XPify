package com.xptitans.xpify.feature_xpify.presentation.add_edit_habit

import androidx.compose.ui.focus.FocusState

sealed class AddEditHabitEvent{
    data class EnteredName(val value: String): AddEditHabitEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditHabitEvent()
    data class EnteredXpAmount(val value: String): AddEditHabitEvent()
    data class ChangeXpAmountFocus(val focusState: FocusState): AddEditHabitEvent()
    data class ChangeColor(val color: Int): AddEditHabitEvent()
    object SaveHabit: AddEditHabitEvent()
}
