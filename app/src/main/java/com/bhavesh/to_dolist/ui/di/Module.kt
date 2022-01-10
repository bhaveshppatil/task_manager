package com.bhavesh.to_dolist.ui.di

import android.content.Context
import androidx.room.Room
import com.bhavesh.to_dolist.ui.data.roomDB.TaskDAO
import com.bhavesh.to_dolist.ui.data.roomDB.TaskRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): TaskRoomDatabase {
        val database = Room.databaseBuilder(
            context,
            TaskRoomDatabase::class.java,
            "task.db"
        )
        database.fallbackToDestructiveMigration()
        return database.build()
    }

    @Singleton
    @Provides
    fun provideDAO(database: TaskRoomDatabase): TaskDAO {
        return database.getRoutineDAO()
    }
}