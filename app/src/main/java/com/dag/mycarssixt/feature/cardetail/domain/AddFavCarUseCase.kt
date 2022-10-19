package com.dag.mycarssixt.feature.cardetail.domain

import com.dag.mycarssixt.base.domain.FlowUseCase
import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.feature.cardetail.data.CarDetailRepository
import com.dag.mycarssixt.network.BaseResult
import com.dag.mycarssixt.network.localrepo.FavCar
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddFavCarUseCase @Inject constructor(
    private val carDetailRepository: CarDetailRepository
): FlowUseCase<AddFavCarUseCase.AddFavCarUseCaseParams,BaseResult<None>>(){

    data class AddFavCarUseCaseParams(
        var favCar: FavCar
    )

    override fun buildUseCase(params: AddFavCarUseCaseParams): Flow<BaseResult<None>> =
        carDetailRepository.addFavCar(params.favCar)
}