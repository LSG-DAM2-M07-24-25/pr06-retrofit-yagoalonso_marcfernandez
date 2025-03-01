package com.example.gotapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gotapp.api.RepositoryApi
import com.example.gotapp.model.CharacterData
import com.example.gotapp.room.GoTDatabase
import com.example.gotapp.room.RepositoryRoom
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class APIViewModel(application: Application) : AndroidViewModel(application) {
    private val repositoryApi = RepositoryApi()
    private val repositoryRoom = RepositoryRoom(GoTDatabase.getDatabase(application).characterDao())

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _characters = MutableLiveData<List<CharacterData>>()
    val characters: LiveData<List<CharacterData>> = _characters

    private val _deadCharacters = MutableLiveData<List<CharacterData>>()
    val deadCharacters: LiveData<List<CharacterData>> = _deadCharacters

    init {
        loadInitialData()
        observeCharacters()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = repositoryApi.getAllCharacters()
                if (response.isSuccessful) {
                    response.body()?.let { apiCharacters ->
                        // Solo insertamos si la base de datos está vacía
                        if (_characters.value.isNullOrEmpty()) {
                            repositoryRoom.insertCharacters(apiCharacters.map { 
                                it.copy(isDead = false) 
                            })
                        }
                    }
                }
            } finally {
                _loading.value = false
            }
        }
    }

    private fun observeCharacters() {
        viewModelScope.launch {
            repositoryRoom.getAllCharacters().collect { allCharacters ->
                _characters.value = allCharacters  // Ahora mostramos todos los personajes
            }
        }
        viewModelScope.launch {
            repositoryRoom.getDeadCharacters().collect { deadOnes ->
                _deadCharacters.value = deadOnes
            }
        }
    }

    fun killCharacter(characterId: Int) {
        viewModelScope.launch {
            repositoryRoom.updateCharacterDeathStatus(characterId, true)
        }
    }

    fun reviveCharacter(characterId: Int) {
        viewModelScope.launch {
            repositoryRoom.updateCharacterDeathStatus(characterId, false)
        }
    }
}