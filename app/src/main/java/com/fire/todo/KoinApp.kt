package com.fire.todo

import android.app.Application
import androidx.room.Database
import androidx.room.Room
//import com.fire.todo.data.TodoDatabase
import com.fire.todo.ui.theme.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.jvm.java

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApp)
            modules(appModule)
        }
    }

}