package com.dag.mycarssixt.feature.profile.fragment.domain

import com.dag.mycarssixt.base.domain.FlowUseCase
import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.feature.profile.fragment.data.ProfileRepository
import com.dag.mycarssixt.network.BaseResult
import com.dag.mycarssixt.network.localrepo.FavCar
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCarsUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : FlowUseCase<None, BaseResult<List<FavCar>>>() {
    override fun buildUseCase(params: None): Flow<BaseResult<List<FavCar>>> =
        profileRepository.getAllCars()
}