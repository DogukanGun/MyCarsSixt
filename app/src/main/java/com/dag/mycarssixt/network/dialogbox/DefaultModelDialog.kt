package com.dag.mycarssixt.network.dialogbox

import androidx.annotation.StringRes

data class DefaultModelDialog(
    @StringRes override val titleRes:Int? = null,
    @StringRes override val messageRes: Int? = null,
    override val negativeButton: ModelDialogButton? = null,
    override val positiveButton: ModelDialogButton,
    override val isCancelable: Boolean = true,
    override val isIconVisible: Boolean = true,
): ModelDialog