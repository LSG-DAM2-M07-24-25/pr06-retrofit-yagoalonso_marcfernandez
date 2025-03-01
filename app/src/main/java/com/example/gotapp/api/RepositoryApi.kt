package com.example.gotapp.api

import com.example.gotapp.model.CharacterData
import retrofit2.Response

class RepositoryApi {
    private val api = APIInterface.create()

    suspend fun getAllCharacters(): Response<List<CharacterData>> {
        return api.getCharacters()
    }
}