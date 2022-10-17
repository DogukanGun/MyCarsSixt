package com.dag.mycarssixt.network.dialogbox

import androidx.annotation.StringRes

data class ModelDialogButton(
    @StringRes val textRes: Int,
    val onClick:(()->Unit)? = null
)
