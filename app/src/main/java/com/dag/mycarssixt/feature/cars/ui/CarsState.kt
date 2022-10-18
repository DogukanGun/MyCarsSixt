package com.dag.mycarssixt.feature.cars.ui

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.feature.cars.data.Car

sealed class CarsState: MyCarsSixtViewState{
    class Cars(val carsList: List<Car>): CarsState()
}
