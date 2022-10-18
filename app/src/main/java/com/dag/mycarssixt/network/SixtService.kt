package com.dag.mycarssixt.network

import com.dag.mycarssixt.feature.cars.data.Car
import retrofit2.http.POST

interface SixtService {

    @POST("")
    fun getCars(): BaseResult<List<Car>>
}