package com.dag.mycarssixt.base.ui

import android.app.Activity
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ext.*
import kotlinx.android.synthetic.main.alert_dialog_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyCarsSixtDialogBox @Inject constructor() {

    companion object {

        private val activeDialogs = mutableListOf<ClDialog>()

        fun showAlertDialog(
            activity: Activity,
            title: String,
            message: String,
            positiveButtonTitle: String?,
            negativeButtonTitle: String?,
            buttonClickListener: ButtonClickListener = object : ButtonClickListener() {},
            isCancelable: Boolean = true,
            isIconVisible: Boolean = true,
            create: Boolean = false,
            dialogKey: String?,
            iconNextToText: IconStatus? = null,
            backToPreviousIcon: Boolean? = null
        ): AlertDialog? {
            if (activity.isFinishing) {
                return null
            }
            val builder = AlertDialog.Builder(activity)
            val dialogView = activity.layoutInflater.inflate(R.layout.alert_dialog_layout, null)
            if (isIconVisible) {
                dialogView.alertDialogErrorIcon.setImageResource(R.drawable.ic_xmark_solid)
            } else {
                dialogView.alertDialogErrorIcon.visibility = View.GONE
            }
            builder.setView(dialogView)
            builder.setCancelable(isCancelable)
            dialogView.alertDialogTitle.setTextColor(R.color.yellow)
            dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_orange)
            dialogView.alertDialogNegativeButton.setTextColor(R.color.black)

            dialogView.alertDialogPositiveButton.setBackgroundResource(R.drawable.dialog_box_button_background_orange)
            dialogView.alertDialogPositiveButton.setTextColor(R.color.black)
            val alertDialog = builder.create()
            val clDialog = ClDialog(alertDialog, dialogKey)
            activeDialogs.add(clDialog)
            dialogView.alertDialogTitle.text = title
            dialogView.alertDialogMessage.text = message
            if (iconNextToText?.primaryIcon != 0) {
                dialogView.alertDialogErrorIcon.makeVisible()
                dialogView.alertDialogErrorIcon.setBackground(iconNextToText?.primaryIcon)
            } else {
                dialogView.alertDialogErrorIcon.makeGone()
            }
            positiveButtonTitle?.let { title ->
                dialogView.alertDialogPositiveButton.text = title
                dialogView.alertDialogPositiveButton.setOnClickListener {
                    buttonClickListener.onPositiveButtonClicked()
                    alertDialog.dismiss()
                    activeDialogs.remove(clDialog)
                    clearDuplicateDialogs(dialogKey)
                }
            } ?: run {
                dialogView.alertDialogPositiveButton.makeGone()
            }
            negativeButtonTitle?.let { title ->
                dialogView.alertDialogNegativeButton.text = title
                dialogView.alertDialogNegativeButton.setOnClickListener {
                    buttonClickListener.onNegativeButtonClicked()
                    alertDialog.dismiss()
                    activeDialogs.remove(clDialog)
                    clearDuplicateDialogs(dialogKey)
                }
            } ?: run {
                dialogView.alertDialogNegativeButton.makeGone()
            }
            dialogView.alertDialogErrorIcon.setOnClickListener {
                buttonClickListener.onIconNextToTextClicked()
                dialogView.alertDialogErrorIcon.setBackground(iconNextToText?.lastIcon)
                if (backToPreviousIcon == true) {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2000L)
                        dialogView.alertDialogErrorIcon.setBackground(iconNextToText?.primaryIcon)
                    }
                }
            }
            dialogView.setPadding(0, 0, 0, 0)
            return if (create) {
                alertDialog
            } else {
                alertDialog.show()
                return null
            }
        }

        fun clearDuplicateDialogs(dialogKey: String? = null) {
            dialogKey?.let { dialogKey ->
                activeDialogs.filter {
                    it.dialogKey?.equals(dialogKey) == true
                }.forEach {
                    it.dialog.dismiss()
                    activeDialogs.remove(it)
                }
            }
        }

        fun clearDialogs() {
            activeDialogs.clear()
        }

        fun dismissDialogs() {
            activeDialogs.map {
                it.dialog.dismiss()
            }
        }

        fun hasActiveDialogs() = activeDialogs.size > 0
    }

    data class ClDialog(
        val dialog: AlertDialog,
        val dialogKey: String?
    )

    data class DialogButtonData(
        val positiveButtonTitle: String?,
        val negativeButtonTitle: String?,
        val buttonClickListener: ButtonClickListener
    )

    data class IconStatus(
        @DrawableRes val primaryIcon: Int?,
        @DrawableRes val lastIcon: Int?
    )

    abstract class ButtonClickListener {
        open fun onPositiveButtonClicked() {
            clearDuplicateDialogs()
        }

        open fun onNegativeButtonClicked() {
            clearDuplicateDialogs()
        }

        open fun onIconNextToTextClicked(@DrawableRes icon: Int? = null) {
            clearDuplicateDialogs()
        }
    }
}