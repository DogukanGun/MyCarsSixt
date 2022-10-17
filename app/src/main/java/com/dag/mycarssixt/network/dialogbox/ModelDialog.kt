package com.dag.mycarssixt.network.dialogbox

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState


interface ModelDialog: MyCarsSixtViewState {

    val titleRes:Int?
    val messageRes:Int?
    val negativeButton: ModelDialogButton?
    val positiveButton: ModelDialogButton
    val isCancelable:Boolean
    val isIconVisible: Boolean
}