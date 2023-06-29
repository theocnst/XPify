package com.xptitans.xpify.feature_xpify.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.xptitans.xpify.feature_xpify.data.data_source.HabitDatabase
import com.xptitans.xpify.feature_xpify.presentation.habit.HabitsViewModel
import com.xptitans.xpify.feature_xpify.navigation.graphs.RootNavigationGraph
import com.xptitans.xpify.ui.theme.XPifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XPifyTheme {
                val navController = rememberNavController()
                RootNavigationGraph(navController = navController)
            }
        }
    }
}