package com.dag.mycarssixt.feature.cardetail.domain

import com.dag.mycarssixt.base.domain.FlowUseCase
import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.feature.cardetail.data.CarDetailRepository
import com.dag.mycarssixt.network.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DislikeCarUseCase @Inject constructor(
    private val carDetailRepository: CarDetailRepository
): FlowUseCase<DislikeCarUseCase.DislikeCarUseCaseParams,BaseResult<None>>(){

    data class DislikeCarUseCaseParams(
        var favCarId:String
    )

    override fun buildUseCase(params: DislikeCarUseCaseParams): Flow<BaseResult<None>> =
        carDetailRepository.dislikeFavCar(params.favCarId)
}