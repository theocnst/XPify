package com.xptitans.xpify.feature_xpify.domain.util

sealed class HabitOrder(
    val orderType: OrderType
){
    class Name(orderType: OrderType): HabitOrder(orderType)
    class XpAmount(orderType: OrderType): HabitOrder(orderType)
    class Duration(orderType: OrderType): HabitOrder(orderType)
}
