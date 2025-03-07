package com.example.gotapp.api

import com.example.gotapp.model.CharacterData
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
// per la trucada a la api
interface APIInterface {
    @GET("Characters")
    suspend fun getCharacters(): Response<List<CharacterData>>

    companion object {
        private const val BASE_URL = "https://thronesapi.com/api/v2/"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}