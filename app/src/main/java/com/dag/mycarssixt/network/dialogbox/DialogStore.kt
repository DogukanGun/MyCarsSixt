package com.dag.mycarssixt.network.dialogbox

import com.dag.mycarssixt.R


fun createRepeatableDialog(
    titleRes: Int = R.string.app_name,
    messageRes: Int = R.string.repeatable_dialog_error_message,
    positiveButton: ModelDialogButton = ModelDialogButton(textRes = R.string.app_name),
    negativeButton: ModelDialogButton? = null,
    isCancelable: Boolean = false,
    isIconVisible: Boolean = true,
    repeatOnPositiveButtonClick: Boolean = true,
    repeatOnNegativeButtonClick: Boolean = false,
    repeatOnCancel: Boolean = false,
): RepeatableModelDialog = DefaultRepeatableModelDialog(
    titleRes = titleRes,
    messageRes = messageRes,
    negativeButton = negativeButton,
    positiveButton = positiveButton,
    isCancelable = isCancelable,
    isIconVisible = isIconVisible,
    repeatOnPositiveButtonClick = repeatOnPositiveButtonClick,
    repeatOnNegativeButtonClick = repeatOnNegativeButtonClick,
    repeatOnCancel = repeatOnCancel
)

fun createGenericDialog(
    titleRes: Int = R.string.app_name,
    messageRes: Int = R.string.network_error_message_text,
    positiveButton: ModelDialogButton = ModelDialogButton(textRes = R.string.dialog_positive_button),
    negativeButton: ModelDialogButton? = null,
    isCancelable: Boolean = false,
): ModelDialog = DefaultModelDialog(
    titleRes = titleRes,
    messageRes = messageRes,
    positiveButton = positiveButton,
    negativeButton = negativeButton,
    isCancelable = isCancelable,
)