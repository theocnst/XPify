package com.xptitans.xpify.feature_xpify.data.repository

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
        dao.insertHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
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