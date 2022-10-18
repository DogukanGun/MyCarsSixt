package com.dag.mycarssixt.feature.carsmap.data

import com.google.android.gms.maps.model.LatLng

data class CarMarker(
    val group: String,
    val series: String,
    val latitude: Double,
    val longitude: Double,
    val licensePlate: String,
){
    val latLong by lazy{
        LatLng(latitude,longitude)
    }

    val placeName by lazy {
        group.plus(",").plus(series)
    }
}
