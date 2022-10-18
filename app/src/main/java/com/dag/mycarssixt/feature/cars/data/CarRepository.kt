package com.dag.mycarssixt.feature.cars.data

import com.dag.mycarssixt.network.BaseRepository
import com.dag.mycarssixt.network.SixtLocalService
import com.dag.mycarssixt.network.SixtService
import javax.inject.Inject

class CarRepository @Inject constructor(
    private val sixtService: SixtService,
) : BaseRepository() {
    fun getCars() = fetch {
        sixtService.getCars()
    }
}