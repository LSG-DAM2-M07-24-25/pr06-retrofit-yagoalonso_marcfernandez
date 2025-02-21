package com.example.gotapp.viewmodel

import android.util.Log
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

    val loading = MutableLiveData(true)
    val characters = MutableLiveData<List<CharacterData>>()

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllCharacters()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        characters.value = data ?: emptyList()
                        loading.value = false
                        Log.d("APIViewModel", "Datos recibidos: ${data?.size}")
                    } else {
                        loading.value = false
                        Log.e("Error API", "Error: ${response.code()} - ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    loading.value = false
                    Log.e("Error API", "Excepción: ${e.localizedMessage}")
                }
            }
        }
    }
}