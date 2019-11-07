package com.example.testeappmoove.data.network

import com.example.testeappmoove.data.entities.MovieDetails
import com.example.testeappmoove.data.entities.MovieResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int = 1): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Call<MovieDetails>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): Call<MovieResponse>

    // Singleton
    companion object {
        operator fun invoke(): MovieApi {

            val authInterceptor = Interceptor { chain ->
                // Adiciona api_key em todas as requisições
                // O correto seria colocar a api_key em um arquivo que não vai para o repositório

                val newUrl = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key", "5bdb76e8bfee733ddafc24d0382e918d")
                    .addQueryParameter("language", "pt-BR")
                    .build()

                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }

            val client = OkHttpClient()
                .newBuilder()
                .addInterceptor(authInterceptor)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
    }
}