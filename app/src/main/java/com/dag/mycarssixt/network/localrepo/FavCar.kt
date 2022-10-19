package com.dag.mycarssixt.network.localrepo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavCar(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var carId:String,
    var liked:Boolean
)