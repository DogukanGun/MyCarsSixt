package com.dag.mycarssixt.feature.cars.ui

import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.base.ui.MyCarsSixtViewModel
import com.dag.mycarssixt.feature.cars.domain.GetCarsUseCase
import javax.inject.Inject

class CarsVM @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase
) : MyCarsSixtViewModel() {

    fun getCars(){
       getCarsUseCase.observe(None)
            .publishLoading()
            .subscribe {
                it?.let { cars->
                    viewState.postValue(
                        CarsState.Cars(cars)
                    )
                }
            }
    }

}