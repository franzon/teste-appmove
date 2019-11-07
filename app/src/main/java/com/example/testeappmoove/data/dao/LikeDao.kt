package com.example.testeappmoove.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.testeappmoove.data.entities.LikedMovie

@Dao
interface LikeDao {

    @Query("INSERT INTO liked_movies VALUES(:movieId)")
    fun insert(movieId: Int)

    @Query("DELETE FROM liked_movies WHERE id=:movieId")
    fun delete(movieId: Int)

    @Query("SELECT * FROM liked_movies")
    fun all(): LiveData<List<LikedMovie>>
}