package com.dag.mycarssixt.feature.carsmap.domain

import com.dag.mycarssixt.base.domain.FlowUseCase
import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.feature.cars.data.Car
import com.dag.mycarssixt.feature.carsmap.data.CarMarker
import com.dag.mycarssixt.feature.carsmap.data.CarsMapRepository
import com.dag.mycarssixt.network.BaseResult
import com.dag.mycarssixt.network.getData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCarsMapperUseCase @Inject constructor(
    private val carsMapRepository: CarsMapRepository
): FlowUseCase<None,BaseResult<List<Car>>>(){
    override fun buildUseCase(params: None): Flow<BaseResult<List<Car>>> = carsMapRepository.getCars()
}