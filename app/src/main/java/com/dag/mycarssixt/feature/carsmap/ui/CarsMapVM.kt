package com.dag.mycarssixt.feature.carsmap.ui

import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ui.MyCarsSixtViewModel
import com.dag.mycarssixt.component.toolbar.MyCarsSixtToolbarData
import javax.inject.Inject

class CarsMapVM @Inject constructor(): MyCarsSixtViewModel() {

    init {
        setMyCarsSixtToolbarViewData(
            MyCarsSixtToolbarData(
                leftImageRes = R.drawable.ic_baseline_back,
                fullyCenteredTitleRes = R.string.carsmap_title,
                rightImageRes = null
            )
        )
    }
}