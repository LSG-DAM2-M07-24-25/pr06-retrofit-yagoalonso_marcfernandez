// Esta interfaz define las operaciones que podemos hacer con la base de datos
package com.example.gotapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gotapp.model.CharacterData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    // Obtener todos los personajes de la base de datos
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterData>>

    // Obtener solo los personajes que están muertos
    @Query("SELECT * FROM characters WHERE is_dead = 1")
    fun getDeadCharacters(): Flow<List<CharacterData>>

    // Guardar una lista de personajes en la base de datos
    // Si ya existe el personaje, lo reemplaza
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterData>)

    // Actualizar si un personaje está muerto o vivo
    @Query("UPDATE characters SET is_dead = :isDead WHERE id = :characterId")
    suspend fun updateCharacterDeathStatus(characterId: Int, isDead: Boolean)
}