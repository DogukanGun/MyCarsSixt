package com.dag.mycarssixt.component.toolbar

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dag.mycarssixt.R

data class MyCarsSixtToolbarData(
    @DrawableRes val leftImageRes: Int? = null,
    @DrawableRes var rightImageRes: Int? = R.drawable.ic_baseline_person,
    var fullyCenteredTitle: String? = null,
    @StringRes val fullyCenteredTitleRes: Int? = R.string.app_name,
)