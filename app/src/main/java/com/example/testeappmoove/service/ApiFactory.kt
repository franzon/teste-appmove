package com.example.testeappmoove.service

import retrofit2.Retrofit

object ApiFactory {
    val api: TmdbApi = RetrofitFactory.retrofit("https://api.themoviedb.org/3/").create(TmdbApi::class.java)
}