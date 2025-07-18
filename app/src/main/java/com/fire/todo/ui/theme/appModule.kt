
/**
 * This file was separately created to resolve the error, else in tutorial it was merged with koinapp file
 * If any error arise later need to check these 2 files as first priority

**/
package com.fire.todo.ui.theme

import android.app.Application
import androidx.room.Room
//import com.fire.todo.data.TodoDatabase
import com.fire.todo.database.AppDatabase
import com.fire.todo.repositories.TodoRepo
import com.fire.todo.repositories.TodoRepoImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.jvm.java

val appModule = module {
    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java, // Unresolved reference: TodoDatabase , it should be replaced by AppDatabase
            "db"
        ).build()
    }
    single {
        TodoRepoImpl(get())
    }bind TodoRepo::class


    // Add your DAO here if needed:
    // single { get<TodoDatabase>().todoDao() }
}