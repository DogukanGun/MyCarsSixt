package com.dag.mycarssixt.network

import com.dag.mycarssixt.feature.cars.data.Car
import retrofit2.http.GET
import retrofit2.http.POST

interface SixtService {

    @GET("cars")
    suspend fun getCars(): BaseResult<List<Car>>
}