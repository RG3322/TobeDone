package com.fire.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao{
    @Insert
    fun addTodo(todoEntity: TodoEntity)
    @Query("SELECT * from todos")
    fun getTodos(): Flow<List<TodoEntity>>
    @Update
    fun updateTodo(todoEntity: TodoEntity)
    @Delete
    fun deleteTodo(todoEntity: TodoEntity)
}