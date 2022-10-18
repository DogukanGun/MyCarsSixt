package com.dag.mycarssixt.feature.carsmap.ui

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.feature.carsmap.data.CarMarker

sealed class CarsMapViewState:MyCarsSixtViewState{
    class Cars(val carsMarkerList:List<CarMarker>): CarsMapViewState()
}
