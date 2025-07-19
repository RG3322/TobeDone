package com.fire.todo

import android.app.Application
import androidx.room.Room
import com.fire.todo.database.TodoDatabase
import com.fire.todo.repositories.TodoRepo
import com.fire.todo.repositories.TodoRepoImpl
import com.fire.todo.screens.HomeViewmodel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApp)
            modules(
                module {
                    // Provide the Room database
                    single {
                        Room.databaseBuilder(
                            get(),
                            TodoDatabase::class.java,
                            "db"
                        ).build()
                    }

                    // Provide DAO
                    single { get<TodoDatabase>().todoDao() }

                    // Provide Repository
                    single<com.fire.todo.repositories.TodoRepo> { com.fire.todo.repositories.TodoRepoImpl(get()) }

                    // Provide ViewModel with constructor injection
                    //viewModel { HomeViewmodel(get()) }
                }
            )
        }
    }
}