package com.xptitans.xpify.feature_xpify.domain.model

import androidx.compose.ui.graphics.Color
import com.xptitans.xpify.ui.theme.*
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val xpAmount: String,
    val color: Int = Color.LightGray.value.toInt(),
) {
    companion object {
        val habitColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
