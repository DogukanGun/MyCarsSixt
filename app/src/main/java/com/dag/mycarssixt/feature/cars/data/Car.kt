package com.dag.mycarssixt.feature.cars.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarElement (
    val id: String,
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val make: String,
    val group: String,
    val color: String,
    val series: String,
    val fuelLevel: Double,
    val fuelType: String,
    val transmission: String,
    val licensePlate: String,
    val latitude: Double,
    val longitude: Double,
    val innerCleanliness: String,
    val carImageURL: String
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

}