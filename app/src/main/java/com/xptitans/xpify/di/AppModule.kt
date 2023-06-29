package com.xptitans.xpify.di

import android.app.Application
import androidx.room.Room
import com.xptitans.xpify.feature_xpify.data.data_source.HabitDatabase
import com.xptitans.xpify.feature_xpify.data.repository.HabitRepositoryImpl
import com.xptitans.xpify.feature_xpify.domain.repository.HabitRepository
import com.xptitans.xpify.feature_xpify.domain.use_case.AddHabit
import com.xptitans.xpify.feature_xpify.domain.use_case.DeleteHabit
import com.xptitans.xpify.feature_xpify.domain.use_case.GetHabits
import com.xptitans.xpify.feature_xpify.domain.use_case.HabitUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHabitDatabase(app: Application): HabitDatabase {
        return Room.databaseBuilder(
            app,
            HabitDatabase::class.java,
            HabitDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHabitReository(db: HabitDatabase): HabitRepository {
        return HabitRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideHabitUseCases(repository: HabitRepository): HabitUseCases {
        return HabitUseCases(
            getHabits = GetHabits(repository),
            deleteHabit = DeleteHabit(repository),
            addHabit = AddHabit(repository)
        )
    }
}