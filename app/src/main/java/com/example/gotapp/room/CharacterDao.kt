package com.example.gotapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gotapp.model.CharacterData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterData>>

    @Query("SELECT * FROM characters WHERE is_dead = 1")
    fun getDeadCharacters(): Flow<List<CharacterData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterData>)

    @Query("UPDATE characters SET is_dead = :isDead WHERE id = :characterId")
    suspend fun updateCharacterDeathStatus(characterId: Int, isDead: Boolean)
}