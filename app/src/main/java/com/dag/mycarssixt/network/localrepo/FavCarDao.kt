package com.dag.mycarssixt.network.localrepo

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavCarDao {

    @Query("SELECT * from FavCar WHERE carId = :carId")
    fun getItem(carId: String): FavCar

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favCar: FavCar)

    @Query("UPDATE FavCar SET liked = 1 where carId = :carId")
    fun likeTheCar(carId: String)

    @Query("UPDATE FavCar SET liked = 0 where carId = :carId")
    fun dislikeTheCar(carId: String)
}