// Este archivo es como un intermediario entre la app y la base de datos
package com.example.gotapp.room

import com.example.gotapp.model.CharacterData
import com.example.gotapp.room.dao.CharacterDao
import kotlinx.coroutines.flow.Flow

//controlador a laravel per trucar els metodes del dao (rutas de laravel)
class RepositoryRoom(private val characterDao: CharacterDao) {
    // Obtener todos los personajes
    fun getAllCharacters(): Flow<List<CharacterData>> = characterDao.getAllCharacters()
    
    // Obtener solo los personajes muertos
    fun getDeadCharacters(): Flow<List<CharacterData>> = characterDao.getDeadCharacters()
    
    // Guardar una lista de personajes en la base de datos
    suspend fun insertCharacters(characters: List<CharacterData>) {
        characterDao.insertCharacters(characters)
    }
    
    // Cambiar el estado de un personaje a muerto o vivo
    suspend fun updateCharacterDeathStatus(characterId: Int, isDead: Boolean) {
        characterDao.updateCharacterDeathStatus(characterId, isDead)
    }
}