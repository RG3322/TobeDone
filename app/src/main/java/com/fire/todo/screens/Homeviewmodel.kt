package com.fire.todo.screens

import android.R.attr.data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fire.todo.database.TodoEntity
import com.fire.todo.repositories.TodoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewmodel : ViewModel(), KoinComponent {


    private val todoRepo: TodoRepo by inject()


    private val _todos = MutableStateFlow<List<TodoEntity>>(emptyList())
    val todos = _todos.asStateFlow()

    init {
        getTodos()
    }


    fun getTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepo.getTodos().collect { data ->
                _todos.value = data
            }

        }


    }

    fun addTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepo.addTodo(todo)
        }
    }

    fun updateTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepo.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepo.deleteTodo(todo)
        }
    }

//    private val _todo = MutableStateFlow<TodoEntity?>(null)
//    val todo: StateFlow<TodoEntity?> = _todo.asStateFlow()
//
//    fun getTodoById(id: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            todoRepo.getTodoById(id).collect { data ->
//                _todo.value = data
//            }
        }

