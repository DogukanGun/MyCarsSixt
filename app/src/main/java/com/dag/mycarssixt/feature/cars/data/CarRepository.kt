package com.dag.mycarssixt.feature.cars.data

import com.dag.mycarssixt.network.BaseRepository
import com.dag.mycarssixt.network.SixtLocalService
import com.dag.mycarssixt.network.SixtService
import javax.inject.Inject

class CarRepository @Inject constructor(
    //private val sixtService: SixtService
    private val sixtLocalService: SixtLocalService
) : BaseRepository() {
    fun getCars() = fetch {
        sixtLocalService.getCars()
    }
}