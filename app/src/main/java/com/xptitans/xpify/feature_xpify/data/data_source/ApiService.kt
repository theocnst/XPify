package com.xptitans.xpify.feature_xpify.data.data_source

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/habit")
    suspend fun getHabits(): List<Habit>
}