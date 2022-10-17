package com.dag.mycarssixt.network.dialogbox

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState

interface RepeatableModelDialog: MyCarsSixtViewState, RepeatableViewEffect, ModelDialog {
    val repeatOnPositiveButtonClick:Boolean
    val repeatOnNegativeButtonClick:Boolean
    val repeatOnCancel:Boolean
}