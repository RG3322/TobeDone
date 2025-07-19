package com.fire.todo.screens

import android.R.attr.data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fire.todo.KoinApp
import com.fire.todo.database.TodoEntity
import com.fire.todo.repositories.TodoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewmodel : ViewModel(), KoinComponent{
    private val repo : TodoRepo by inject()

    private val _todos : MutableStateFlow<List<TodoEntity>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

        fun addTodo(todoEntity: TodoEntity) {
            viewModelScope.launch(Dispatchers.IO) {
                repo.addTodo(todoEntity)
            }
            }
            fun updateTodo(todoEntity: TodoEntity) {
                viewModelScope.launch(Dispatchers.IO) {
                    repo.updateTodo(todoEntity)
                }
            }





            fun deleteTodo(todoEntity: TodoEntity) {
                viewModelScope.launch() {
                    repo.deleteTodo(todoEntity)
                }

            }

            fun getTodos() {
                viewModelScope.launch(Dispatchers.IO) {
                    repo.getTodos().collect { data ->
                        _todos.update { data }
                    }
                }
            }


        }









