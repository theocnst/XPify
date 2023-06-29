import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xptitans.xpify.feature_xpify.navigation.HabitPageScreen
import com.xptitans.xpify.feature_xpify.navigation.graphs.AuthScreen
import com.xptitans.xpify.feature_xpify.presentation.habit.HabitsEvent
import com.xptitans.xpify.feature_xpify.presentation.habit.HabitsViewModel
import com.xptitans.xpify.feature_xpify.presentation.habit.components.HabitItem
import com.xptitans.xpify.feature_xpify.presentation.habit.components.OrderSection
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun HabitsScreen(
    navController: NavController,
    viewModel: HabitsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 55.dp),
                onClick = {
                    navController.navigate(HabitPageScreen.AddEditHabitScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add habit")
            }
        },
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your habits",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(HabitsEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isHabitOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    habitOrder = state.habitOrder,
                    onOrderChange = {
                        viewModel.onEvent(HabitsEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.habits) { habit ->
                    HabitItem(
                        habit = habit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    HabitPageScreen.AddEditHabitScreen.route +
                                            "?habitId=${habit.id}&habitColor=${habit.color}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(HabitsEvent.DeleteHabit(habit))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Habit deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(HabitsEvent.RestoreHabit)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}