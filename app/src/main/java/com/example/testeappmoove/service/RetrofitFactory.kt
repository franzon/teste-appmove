package com.example.testeappmoove.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val authInterceptor = Interceptor { chain ->
        // Adiciona api_key em todas as requisições
        // O correto seria colocar a api_key em um arquivo que não vai para o repositório

        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", "5bdb76e8bfee733ddafc24d0382e918d")
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}