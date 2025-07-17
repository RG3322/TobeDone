package com.fire.todo.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

// Define Todo entity class. This is a placeholder and should be defined according to your needs.
@androidx.room.Entity
data class Todo(
    @androidx.room.PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)

// Define TodoDao interface. This is a placeholder and should be defined according to your needs.
@androidx.room.Dao
interface TodoDao