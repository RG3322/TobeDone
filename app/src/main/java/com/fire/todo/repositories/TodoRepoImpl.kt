package com.fire.todo.repositories

import com.fire.todo.database.AppDatabase
import com.fire.todo.database.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepoImpl(private val database: AppDatabase): TodoRepo {
   private val todoDao = database.todoDao()
    override suspend fun addTodo(todoEntity: TodoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getTodos(): Flow<List<TodoEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTodo(todoEntity: TodoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodo(todoEntity: TodoEntity) {
        TODO("Not yet implemented")
    }


}