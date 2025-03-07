package com.example.gotapp

import android.app.Application
import androidx.room.Room
import com.example.gotapp.room.GoTDatabase
//inicialitzacio bbdd
class GoTApplication : Application() {
    companion object {
        lateinit var database: GoTDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            GoTDatabase::class.java,
            "got_database"
        ).build()
    }
} 