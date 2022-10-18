package com.dag.mycarssixt.feature.cars.domain

import com.dag.mycarssixt.base.domain.FlowUseCase
import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.feature.cars.data.Car
import com.dag.mycarssixt.feature.cars.data.CarRepository
import com.dag.mycarssixt.network.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarsUseCase @Inject constructor(
    private val carRepository: CarRepository
) : FlowUseCase<None, BaseResult<List<Car>>>() {
    override fun buildUseCase(params: None): Flow<BaseResult<List<Car>>> =
        carRepository.getCars()
}