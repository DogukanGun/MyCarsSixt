package com.dag.mycarssixt.feature.profile.fragment.ui

import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.base.ui.MyCarsSixtViewModel
import com.dag.mycarssixt.feature.profile.fragment.domain.GetAllCarsUseCase
import javax.inject.Inject

class ProfileFragmentVM @Inject constructor(
    private val getAllCarsUseCase: GetAllCarsUseCase
):MyCarsSixtViewModel() {
    fun getAllCars() {
        getAllCarsUseCase.observe(None)
            .publishLoading()
            .subscribe {
                it?.let {
                    viewState.postValue(
                        ProfileFragmentState.FavCars(it)
                    )
                }
            }
    }
}