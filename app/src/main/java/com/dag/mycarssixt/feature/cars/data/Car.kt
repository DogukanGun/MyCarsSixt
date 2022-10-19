package com.dag.mycarssixt.feature.cars.data

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car (
    val id: String = "",
    val modelIdentifier: String = "",
    val modelName: String = "",
    val name: String = "",
    val make: String = "",
    val group: String = "",
    val color: String = "",
    val series: String = "",
    val fuelLevel: Double = 0.0,
    val fuelType: String = "",
    val transmission: String = "",
    val licensePlate: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val innerCleanliness: String = "",
    val carImageUrl: String = ""
): Parcelable{
    val fuelTypeText: String
        get() {
            return when(fuelType){
                "D"->{
                    "Diesel"
                }
                "E"->{
                    "Electrical"
                }
                "P"->{
                    "Petrol"
                }
                else->{
                    "Unknown"
                }
            }
        }

    val transmissionText: String
        get() {
            return when(transmission){
                "A"->{
                    "Automatic"
                }
                "M"->{
                    "Manuel"
                }
                else->{
                    "Unknown"
                }
            }
        }

    val latLong by lazy{
        LatLng(latitude,longitude)
    }

}