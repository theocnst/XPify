package com.xptitans.xpify.feature_xpify.data.data_source

import com.xptitans.xpify.feature_xpify.domain.model.Habit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/v1/habit")
    suspend fun getHabits(): List<Habit>
    @POST("api/v1/habit")
    suspend fun insertHabit(@Body habit: Habit): Habit
    @PUT("api/v1/habit/{id}")
    suspend fun updateHabit(@Path("id") id: Int, @Body habit: Habit): Habit
    @DELETE("api/v1/habit/{id}")
    suspend fun deleteHabit(@Path("id") id: Int)

}