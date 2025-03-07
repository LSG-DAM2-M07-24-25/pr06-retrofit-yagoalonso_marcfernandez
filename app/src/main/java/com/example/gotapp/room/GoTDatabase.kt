// Este archivo crea y configura la base de datos de la app
package com.example.gotapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gotapp.model.CharacterData
import com.example.gotapp.room.dao.CharacterDao
// Definimos la base de datos con sus tablas y versión
@Database(entities = [CharacterData::class], version = 1, exportSchema = false)
abstract class GoTDatabase : RoomDatabase() {
    // Función para acceder a las operaciones de la base de datos
    abstract fun characterDao(): CharacterDao

    companion object {
        // Variable para guardar la instancia de la base de datos
        @Volatile
        private var INSTANCE: GoTDatabase? = null

        // Función para obtener la base de datos
        // Si no existe, la crea; si existe, la devuelve
        fun getDatabase(context: Context): GoTDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoTDatabase::class.java,
                    "got_database"    // Nombre del archivo de la base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 