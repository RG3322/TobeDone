package com.fire.todo.repositories

import com.fire.todo.database.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepo {

    suspend fun addTodo(todoEntity: TodoEntity)
    suspend fun getTodos(): Flow<List<TodoEntity>>
    suspend fun updateTodo(todoEntity: TodoEntity)
    suspend fun deleteTodo(todoEntity: TodoEntity)

}