// Este ViewModel maneja todos los datos de los personajes
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
    // Conexión con la API y la base de datos
    private val repositoryApi = RepositoryApi()
    private val repositoryRoom = RepositoryRoom(GoTDatabase.getDatabase(application).characterDao())

    // Variable para mostrar/ocultar la pantalla de carga
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // Lista de todos los personajes
    private val _characters = MutableLiveData<List<CharacterData>>()
    val characters: LiveData<List<CharacterData>> = _characters

    // Lista solo de personajes muertos
    private val _deadCharacters = MutableLiveData<List<CharacterData>>()
    val deadCharacters: LiveData<List<CharacterData>> = _deadCharacters

    // Cuando se crea el ViewModel, carga los datos
    init {
        loadInitialData()
        observeCharacters()
    }

    // Carga los personajes desde la API y los guarda en la base de datos
    private fun loadInitialData() {
        viewModelScope.launch {
            _loading.value = true  // Mostrar pantalla de carga
            try {
                val response = repositoryApi.getAllCharacters()
                if (response.isSuccessful) {
                    response.body()?.let { apiCharacters ->
                        // Solo guarda los personajes si la base de datos está vacía
                        if (_characters.value.isNullOrEmpty()) {
                            repositoryRoom.insertCharacters(apiCharacters.map { 
                                it.copy(isDead = false)  // Al inicio, nadie está muerto
                            })
                        }
                    }
                }
            } finally {
                _loading.value = false  // Ocultar pantalla de carga
            }
        }
    }

    // Observa los cambios en la base de datos
    private fun observeCharacters() {
        // Observa todos los personajes
        viewModelScope.launch {
            repositoryRoom.getAllCharacters().collect { allCharacters ->
                _characters.value = allCharacters
            }
        }
        // Observa solo los personajes muertos
        viewModelScope.launch {
            repositoryRoom.getDeadCharacters().collect { deadOnes ->
                _deadCharacters.value = deadOnes
            }
        }
    }

    // Función para marcar un personaje como muerto
    fun killCharacter(characterId: Int) {
        viewModelScope.launch {
            repositoryRoom.updateCharacterDeathStatus(characterId, true)
        }
    }

    // Función para revivir un personaje
    fun reviveCharacter(characterId: Int) {
        viewModelScope.launch {
            repositoryRoom.updateCharacterDeathStatus(characterId, false)
        }
    }
}