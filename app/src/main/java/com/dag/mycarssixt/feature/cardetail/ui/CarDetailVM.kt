package com.dag.mycarssixt.feature.cardetail.ui

import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ui.MyCarsSixtViewModel
import com.dag.mycarssixt.component.toolbar.MyCarsSixtToolbarData
import javax.inject.Inject

class CarDetailVM @Inject constructor(): MyCarsSixtViewModel() {

    fun changeToolbar(title:String) {
        setMyCarsSixtToolbarViewData(
            MyCarsSixtToolbarData(
                leftImageRes = R.drawable.ic_baseline_back,
                fullyCenteredTitle = title,
                rightImageRes = null
            )
        )
    }

}