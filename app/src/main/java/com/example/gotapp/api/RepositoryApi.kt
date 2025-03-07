package com.example.gotapp.api

import com.example.gotapp.model.CharacterData
import retrofit2.Response

// per guardar les dades de la api a la base de dades
class RepositoryApi {
    private val api = APIInterface.create()

    suspend fun getAllCharacters(): Response<List<CharacterData>> {
        return api.getCharacters()
    }
}