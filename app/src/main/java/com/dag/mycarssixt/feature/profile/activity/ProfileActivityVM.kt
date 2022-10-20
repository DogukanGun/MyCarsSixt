package com.dag.mycarssixt.feature.profile.activity

import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ui.MyCarsSixtViewModel
import com.dag.mycarssixt.component.toolbar.MyCarsSixtToolbarData
import javax.inject.Inject

class ProfileActivityVM @Inject constructor() : MyCarsSixtViewModel() {
    init {
        setMyCarsSixtToolbarViewData(
            MyCarsSixtToolbarData(
                leftImageRes = R.drawable.ic_baseline_back,
                fullyCenteredTitleRes = R.string.profile_page_title,
                rightImageRes = null
            )
        )
    }
}