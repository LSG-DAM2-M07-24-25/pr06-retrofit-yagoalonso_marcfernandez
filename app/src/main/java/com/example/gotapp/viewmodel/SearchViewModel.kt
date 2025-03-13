package com.example.gotapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    // Variable para el texto que se está buscando actualmente
    private val _searchedText = MutableLiveData("")
    val searchedText: LiveData<String> = _searchedText

    // Lista que guarda el historial de búsquedas
    private val _searchHistory = MutableLiveData<List<String>>(emptyList())
    val searchHistory: LiveData<List<String>> = _searchHistory
    // Función que se llama cada vez que el texto de búsqueda cambia
    fun onSearchTextChange(text: String) {
        this._searchedText.value = text
    }

    // Función para añadir una búsqueda al historial
    // Añade la nueva búsqueda al principio de la lista
    fun addToHistory(text: String) {
        if (text.isNotBlank()) {
            val currentHistory = _searchHistory.value.orEmpty() // Obtiene la lista actual o una lista vacía
            this._searchHistory.value = listOf(text) + currentHistory // Añade el nuevo texto al principio
            this._searchedText.value = "" // Limpia el texto después de hacer la búsqueda
        }
    }

    // Función para limpiar todo el historial de búsquedas
    fun clearHistory() {
        this._searchHistory.value = emptyList()
    }
} 