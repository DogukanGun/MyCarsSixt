package com.dag.mycarssixt.feature.profile.fragment.ui

import androidx.annotation.LayoutRes
import com.dag.mycarssixt.base.adapter.ListItem
import com.dag.mycarssixt.network.localrepo.FavCar

data class ProfileRowItem(
    @LayoutRes val rowLayout:Int,
    val favCar: FavCar? = null
):ListItem(rowLayout)