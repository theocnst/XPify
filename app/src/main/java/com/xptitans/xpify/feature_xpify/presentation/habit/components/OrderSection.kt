package com.xptitans.xpify.feature_xpify.presentation.habit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xptitans.xpify.feature_xpify.domain.util.HabitOrder
import com.xptitans.xpify.feature_xpify.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    habitOrder: HabitOrder = HabitOrder.Name(OrderType.Ascending),
    onOrderChange: (HabitOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Name",
                selected = habitOrder is HabitOrder.Name,
                onSelect = {
                    onOrderChange(HabitOrder.Name(habitOrder.orderType))
                }
            )
            DefaultRadioButton(
                text = "Xp amount",
                selected = habitOrder is HabitOrder.XpAmount,
                onSelect = {
                    onOrderChange(HabitOrder.XpAmount(habitOrder.orderType))
                }
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = habitOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(habitOrder.copy(OrderType.Ascending))
                }
            )
            DefaultRadioButton(
                text = "Descending",
                selected = habitOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(habitOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}