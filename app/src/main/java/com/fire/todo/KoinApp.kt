package com.fire.todo

import android.app.Application
import androidx.room.Room
import com.fire.todo.database.TodoDatabase
import com.fire.todo.repositories.TodoRepo
import com.fire.todo.repositories.TodoRepoImpl
//import com.fire.todo.data.TodoDatabase
//import com.fire.todo.ui.theme.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.jvm.java

open class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApp)
            modules(
                module {
                    single {
                        Room.databaseBuilder(
                            get<Application>(),
                            TodoDatabase::class.java, // Unresolved reference: TodoDatabase , it should be replaced by AppDatabase
                            "db"
                        ).build()
                    }
                    single{get<TodoDatabase>().todoDao()}//provide DAO from room

                    single<TodoRepo> { TodoRepoImpl(get()) } //provide repository


                }
            )
        }
    }

}