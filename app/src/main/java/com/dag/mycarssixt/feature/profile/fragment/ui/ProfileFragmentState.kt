package com.dag.mycarssixt.feature.profile.fragment.ui

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.network.localrepo.FavCar

sealed class ProfileFragmentState:MyCarsSixtViewState {
    class FavCars(val favCars:List<FavCar>): ProfileFragmentState()
}