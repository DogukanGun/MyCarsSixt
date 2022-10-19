package com.dag.mycarssixt.feature.cardetail.domain

import com.dag.mycarssixt.base.domain.FlowUseCase
import com.dag.mycarssixt.feature.cardetail.data.CarDetailRepository
import com.dag.mycarssixt.network.BaseResult
import com.dag.mycarssixt.network.localrepo.FavCar
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavCarUseCase @Inject constructor(
    private val carDetailRepository: CarDetailRepository
): FlowUseCase<GetFavCarUseCase.GetFavCarUseCaseParams,BaseResult<FavCar>>(){

    data class GetFavCarUseCaseParams(
        var favCarId:String
    )

    override fun buildUseCase(params: GetFavCarUseCaseParams): Flow<BaseResult<FavCar>> =
        carDetailRepository.getFavCar(params.favCarId)
}