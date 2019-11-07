package com.example.testeappmoove.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_videos")
data class LikedMovie(
    @PrimaryKey()
    val id: Int
)