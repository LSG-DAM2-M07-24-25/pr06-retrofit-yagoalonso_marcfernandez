// Este archivo inicia la base de datos cuando se abre la app
package com.example.gotapp

import android.app.Application
import androidx.room.Room
import com.example.gotapp.room.GoTDatabase
//inicialitzacio bbdd
class GoTApplication : Application() {
    companion object {
        // Variable global para acceder a la base de datos desde cualquier parte
        lateinit var database: GoTDatabase
    }

    // Función que se ejecuta cuando se inicia la app
    override fun onCreate() {
        super.onCreate()
        // Crear la base de datos
        database = Room.databaseBuilder(
            this,
            GoTDatabase::class.java,
            "got_database"    // Nombre del archivo de la base de datos
        ).build()
    }
} 