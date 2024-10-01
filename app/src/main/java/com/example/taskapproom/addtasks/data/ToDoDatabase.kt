package com.example.taskapproom.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class ToDoDatabase:RoomDatabase(){
    abstract fun taskDao():TaskDao
}