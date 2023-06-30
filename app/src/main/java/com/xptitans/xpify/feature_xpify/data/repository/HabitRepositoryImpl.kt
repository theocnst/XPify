package com.xptitans.xpify.feature_xpify.data.repository

import android.util.Log
import com.xptitans.xpify.feature_xpify.data.data_source.ApiService
import com.xptitans.xpify.feature_xpify.data.data_source.HabitDao
import com.xptitans.xpify.feature_xpify.domain.model.Habit
import com.xptitans.xpify.feature_xpify.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HabitRepositoryImpl(
    private val dao: HabitDao
) : HabitRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://649d4c7a9bac4a8e669d85bf.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    override suspend fun insertHabit(habit: Habit) {
        if (habit.id == null) {
            // If new habit, attempt to insert into API and then into the Room database
            try {
                val createdHabit = apiService.insertHabit(habit)
                dao.insertHabit(createdHabit)
            } catch (e: Exception) {
                Log.d("Error inserting habit into the API:", "${e.message}")
                dao.insertHabit(habit)
            }
        } else {
            // Existing habit, so update in both the API and Room database
            try {
                val updatedHabit = apiService.updateHabit(habit.id, habit)
                dao.insertHabit(updatedHabit)
            } catch (e: Exception) {
                Log.d("Error updating habit in the API:", "${e.message}")

                // When restoring a deleted habit
                val createdHabit = apiService.insertHabit(habit)
                dao.insertHabit(createdHabit)
            }
        }
    }

    override suspend fun deleteHabit(habit: Habit) {
        try {
            if (habit.id != null) {
                apiService.deleteHabit(habit.id)
            }
        } catch (e: Exception) {
            Log.d("Error deleting habit from the API:", " ${e.message}")
        }
        dao.deleteHabit(habit)
    }

    override fun getHabits(): Flow<List<Habit>> {
        return dao.getHabits()
    }

    override suspend fun getHabitById(id: Int): Habit? {
        return dao.getHabitById(id)
    }

    override suspend fun refreshHabits() {
        val habits = apiService.getHabits()
        habits.forEach { habit ->
            dao.insertHabit(habit)
        }
    }
}