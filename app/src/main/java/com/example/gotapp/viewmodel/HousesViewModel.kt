package com.example.gotapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gotapp.api.RepositoryHouses
import com.example.gotapp.model.HouseData

class HousesViewModel : ViewModel() {
    private val repository = RepositoryHouses()
    
    private val _houses = MutableLiveData<List<HouseData>>()
    val houses: LiveData<List<HouseData>> = _houses
    
    init {
        loadHouses()
    }
    
    private fun loadHouses() {
        _houses.value = repository.getHouses()
    }
    
    fun getHouseById(id: Int): HouseData? {
        return houses.value?.find { it.id == id }
    }
} 