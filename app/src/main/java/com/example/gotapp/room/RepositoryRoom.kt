package com.example.gotapp.room

import com.example.gotapp.model.CharacterData
import com.example.gotapp.room.dao.CharacterDao
import kotlinx.coroutines.flow.Flow

class RepositoryRoom(private val characterDao: CharacterDao) {
    fun getAllCharacters(): Flow<List<CharacterData>> = characterDao.getAllCharacters()
    
    fun getDeadCharacters(): Flow<List<CharacterData>> = characterDao.getDeadCharacters()
    
    suspend fun insertCharacters(characters: List<CharacterData>) {
        characterDao.insertCharacters(characters)
    }
    
    suspend fun updateCharacterDeathStatus(characterId: Int, isDead: Boolean) {
        characterDao.updateCharacterDeathStatus(characterId, isDead)
    }
}