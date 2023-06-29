package com.xptitans.xpify.feature_xpify.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
