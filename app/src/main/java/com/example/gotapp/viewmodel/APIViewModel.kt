package com.example.gotapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotapp.api.Repository
import com.example.gotapp.model.CharacterData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()

    private val _characters = MutableLiveData<List<CharacterData>>()
    val characters: LiveData<List<CharacterData>> = _characters

    private val _deadCharacters = MutableLiveData<List<CharacterData>>()
    val deadCharacters: LiveData<List<CharacterData>> = _deadCharacters

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _deathMessage = MutableLiveData<String>()
    val deathMessage: LiveData<String> = _deathMessage

    init {
        _deadCharacters.value = emptyList()
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllCharacters()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        _characters.value = data?.filter { character ->
                            !(_deadCharacters.value?.contains(character) ?: false)
                        } ?: emptyList()
                        _loading.value = false
                        Log.d("APIViewModel", "Datos recibidos: ${data?.size}")
                    } else {
                        _loading.value = false
                        Log.e("Error API", "Error: ${response.code()} - ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    Log.e("Error API", "Excepción: ${e.localizedMessage}")
                }
            }
        }
    }

    fun addDeadCharacter(character: CharacterData) {
        val currentDeadList = _deadCharacters.value.orEmpty().toMutableList()
        if (!currentDeadList.contains(character)) {
            currentDeadList.add(character)
            _deadCharacters.value = currentDeadList

            val currentLiveList = _characters.value.orEmpty().toMutableList()
            currentLiveList.remove(character)
            _characters.value = currentLiveList

            _deathMessage.value = "${character.fullName} ha muerto"
        }
    }

    fun clearDeathMessage() {
        _deathMessage.value = ""
    }
}