package com.xptitans.xpify.feature_xpify.navigation

sealed class HabitPageScreen(val route: String) {
    object HabitsScreen : HabitPageScreen("habits_screen")
    object AddEditHabitScreen : HabitPageScreen("add_edit_habit_screen")
}

