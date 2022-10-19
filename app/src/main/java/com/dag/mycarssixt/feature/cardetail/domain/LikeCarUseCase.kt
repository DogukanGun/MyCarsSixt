package com.dag.mycarssixt.feature.cardetail.domain

import com.dag.mycarssixt.base.domain.FlowUseCase
import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.feature.cardetail.data.CarDetailRepository
import com.dag.mycarssixt.network.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeCarUseCase @Inject constructor(
    private val carDetailRepository: CarDetailRepository
): FlowUseCase<LikeCarUseCase.LikeCarUseCaseParams,BaseResult<None>>() {

    data class LikeCarUseCaseParams(
        val likeCarId:String
    )

    override fun buildUseCase(params: LikeCarUseCaseParams): Flow<BaseResult<None>> =
        carDetailRepository.likeFavCar(params.likeCarId)
}