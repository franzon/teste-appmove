package com.example.testeappmoove.service

object ApiFactory {
    val api: MovieApi = RetrofitFactory.retrofit().create(MovieApi::class.java)
}