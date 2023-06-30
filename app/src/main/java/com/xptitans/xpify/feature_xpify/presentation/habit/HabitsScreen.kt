import android.graphics.RuntimeShader
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xptitans.xpify.feature_xpify.navigation.HabitPageScreen
import com.xptitans.xpify.feature_xpify.presentation.auth.components.CUSTOM_SHADER
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
    val Coral = Color(0xFFF3A397)
    val LightYellow = Color(0xFFF8EE94)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 5.dp),
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
        Box(
            modifier = Modifier
                .drawWithCache {
                    val shader = RuntimeShader(CUSTOM_SHADER)
                    val shaderBrush = ShaderBrush(shader)
                    shader.setFloatUniform("resolution", size.width, size.height)
                    onDrawBehind {
                        shader.setColorUniform(
                            "color",
                            android.graphics.Color.valueOf(
                                LightYellow.red, LightYellow.green,
                                LightYellow
                                    .blue,
                                LightYellow.alpha
                            )
                        )
                        shader.setColorUniform(
                            "color2",
                            android.graphics.Color.valueOf(
                                Coral.red,
                                Coral.green,
                                Coral.blue,
                                Coral.alpha
                            )
                        )
                        drawRect(shaderBrush)
                    }
                }
                .fillMaxWidth(),
            content = {
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
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
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(HabitsEvent.RefreshHabits)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Refresh habits"
                                )
                            }
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
        )
    }
}
