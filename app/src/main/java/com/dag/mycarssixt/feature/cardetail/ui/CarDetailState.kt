package com.dag.mycarssixt.feature.cardetail.ui

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.network.localrepo.FavCar

sealed class CarDetailState: MyCarsSixtViewState{
    class CarLikeStatus(val favCar: FavCar): CarDetailState()
}