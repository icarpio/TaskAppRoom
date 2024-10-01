package com.example.taskapproom.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.example.taskapproom.addtasks.data.TaskDao
import com.example.taskapproom.addtasks.data.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTaskDao(toDoDatabase: ToDoDatabase): TaskDao {
        return toDoDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideToçdoDatabase(@ApplicationContext appContext:Context): ToDoDatabase {
        return Room.databaseBuilder(appContext, ToDoDatabase::class.java,"TaskDatabase").build()
    }
}