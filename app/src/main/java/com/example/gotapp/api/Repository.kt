package com.example.gotapp.api

import android.provider.ContactsContract.Contacts.Data
import retrofit2.Response

class Repository {
    private val apiInterface = APIInterface.create()

    suspend fun getAllCharacters(): Response<Data> {
        return apiInterface.getCharacters()
    }
}