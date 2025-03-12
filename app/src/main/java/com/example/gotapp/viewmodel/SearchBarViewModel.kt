package com.example.gotapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchBarViewModel : ViewModel() {
    // Variable para el texto que se está buscando
    private val _searchText = MutableLiveData<String>("")
    val searchText: LiveData<String> = _searchText

    // Variable para guardar el historial de búsquedas
    private val _searchHistory = MutableLiveData<List<String>>(emptyList())
    val searchHistory: LiveData<List<String>> = _searchHistory

    // Función que se llama cada vez que el texto de búsqueda cambia
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    // Función para añadir una búsqueda al historial
    fun addToHistory(search: String) {
        if (search.isNotBlank()) {
            val currentHistory = _searchHistory.value ?: emptyList()
            if (!currentHistory.contains(search)) {
                _searchHistory.value = currentHistory + search
            }
        }
    }

    // Función para limpiar el texto de búsqueda
    fun clearSearch() {
        _searchText.value = ""
    }

    // Función para limpiar todo el historial de búsquedas
    fun clearHistory() {
        _searchHistory.value = emptyList()
        clearSearch()
    }

    // Función para filtrar una lista por el texto de búsqueda
    fun <T> filterList(list: List<T>, predicate: (T, String) -> Boolean): List<T> {
        val currentSearchText = searchText.value ?: ""
        return if (currentSearchText.isBlank()) {
            list
        } else {
            list.filter { item -> predicate(item, currentSearchText) }
        }
    }
} 