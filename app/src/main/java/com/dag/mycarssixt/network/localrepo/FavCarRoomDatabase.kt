package com.dag.mycarssixt.network.localrepo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavCar::class], version = 1, exportSchema = false)
abstract class FavCarRoomDatabase: RoomDatabase() {
    abstract fun favCarDao(): FavCarDao

    companion object {
        @Volatile
        private var INSTANCE: FavCarRoomDatabase? = null

        fun getDatabase(context: Context): FavCarRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavCarRoomDatabase::class.java,
                    "favcar_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}