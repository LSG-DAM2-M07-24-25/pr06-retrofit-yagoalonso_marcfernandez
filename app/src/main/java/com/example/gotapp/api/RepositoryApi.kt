package com.example.thronesapp.api

import com.example.thronesapp.model.CharacterData
import retrofit2.Response

class Repository {
    private val api = APIInterface.create()

    suspend fun getAllCharacters(): Response<List<CharacterData>> {
        return api.getCharacters()
    }
}