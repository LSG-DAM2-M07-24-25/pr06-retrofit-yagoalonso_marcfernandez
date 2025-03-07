// Este ViewModel maneja todos los datos de las casas
package com.example.gotapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gotapp.api.RepositoryHouses
import com.example.gotapp.model.HouseData

class HousesViewModel : ViewModel() {
    // Conexión con el repositorio de casas
    private val repository = RepositoryHouses()
    
    // Lista de todas las casas
    private val _houses = MutableLiveData<List<HouseData>>()
    val houses: LiveData<List<HouseData>> = _houses
    
    // Cuando se crea el ViewModel, carga las casas
    init {
        loadHouses()
    }
    
    // Carga todas las casas desde el repositorio
    private fun loadHouses() {
        _houses.value = repository.getHouses()
    }
    
    // Busca una casa por su ID
    fun getHouseById(id: Int): HouseData? {
        return houses.value?.find { it.id == id }
    }
} 