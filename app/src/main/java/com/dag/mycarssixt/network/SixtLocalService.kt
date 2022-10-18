package com.dag.mycarssixt.network

import com.dag.mycarssixt.feature.cars.data.Car
import com.dag.mycarssixt.network.localrepo.carsJson
import com.google.gson.Gson
import javax.inject.Inject

class SixtLocalService @Inject constructor() {

    fun getCars():BaseResult<List<Car>>{
        return BaseResult.Success(
            Gson().fromJson(carsJson,Array<Car>::class.java).toList()
        )
    }
}