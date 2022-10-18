package com.dag.mycarssixt.feature.carsmap.data

import com.dag.mycarssixt.network.BaseRepository
import com.dag.mycarssixt.network.SixtLocalService
import com.dag.mycarssixt.network.SixtService
import javax.inject.Inject

class CarsMapRepository @Inject constructor(
    private val sixtService: SixtService,
) : BaseRepository() {
    fun getCars() = fetch {
        sixtService.getCars()
    }
}