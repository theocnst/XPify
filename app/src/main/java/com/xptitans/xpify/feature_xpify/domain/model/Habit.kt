package com.xptitans.xpify.feature_xpify.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val name: String,
    val xpAmount: Int,
    val duration: Int
)
