package com.dag.mycarssixt.base.ui

import android.content.Context
import com.dag.mycarssixt.component.processdialog.AnimationType.*
import com.dag.mycarssixt.component.processdialog.MyCarsSixtProcessDialog
import javax.inject.Inject

class MyCarsSixtProgressDialogManager @Inject constructor() {

    companion object {
        private var progressDialog: MyCarsSixtProcessDialog? = null
    }

    fun showErrorDialog(context: Context) {
        progressDialog = MyCarsSixtProcessDialog(context, ERROR)
        progressDialog?.show()
        progressDialog?.listener = object : MyCarsSixtProcessDialog.MyCarsSixtAnimationListener {
            override fun finishAnimation() {
                progressDialog?.dismiss()
            }
        }
    }

    fun showUploadDialog(context: Context) {
        progressDialog = MyCarsSixtProcessDialog(context, UPLOAD)
        progressDialog?.show()
        progressDialog?.listener = object : MyCarsSixtProcessDialog.MyCarsSixtAnimationListener {
            override fun finishAnimation() {
                progressDialog?.dismiss()
            }
        }
    }

    fun showLoadingDialog(context: Context) {
        progressDialog = MyCarsSixtProcessDialog(context)
        progressDialog?.show()
    }

    fun stopDialog() {
        progressDialog?.dismiss()
    }
}