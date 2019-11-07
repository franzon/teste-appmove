package com.example.testeappmoove.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testeappmoove.data.dao.LikeDao
import com.example.testeappmoove.data.entities.LikedMovie

@Database(entities = [LikedMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun likeDao(): LikeDao

    // Singleton
    // https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "likes.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}