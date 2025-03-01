package com.example.gotapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gotapp.model.CharacterData
import com.example.gotapp.room.dao.CharacterDao

@Database(entities = [CharacterData::class], version = 1, exportSchema = false)
abstract class GoTDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: GoTDatabase? = null

        fun getDatabase(context: Context): GoTDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoTDatabase::class.java,
                    "got_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 