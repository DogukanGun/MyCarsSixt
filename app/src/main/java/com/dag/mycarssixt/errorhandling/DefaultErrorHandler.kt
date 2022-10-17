package com.dag.mycarssixt.errorhandling


import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.network.*
import com.dag.mycarssixt.network.dialogbox.createGenericDialog
import com.dag.mycarssixt.network.dialogbox.createRepeatableDialog
import java.net.HttpURLConnection
import javax.inject.Inject

open class DefaultErrorHandler @Inject constructor() : ErrorHandler {

    override fun handle(throwable: Throwable): MyCarsSixtViewState? {
        if (throwable !is MyCarsSixtFailure) {
            return null
        }

        return when (throwable) {
            NetworkConnectionFailure -> networkConnectionDialog()
            UnauthenticatedFailure -> networkConnectionDialog()
            SocketTimeoutFailure, is UnexpectedFailure -> createGenericDialog()
            is ApiFailure -> {
                if (throwable.response.code() == HttpURLConnection.HTTP_GATEWAY_TIMEOUT) {
                    createRepeatableDialog()
                } else {
                    networkConnectionDialog()
                }
            }
        }
    }
}


fun networkConnectionDialog(): MyCarsSixtViewState = createGenericDialog(
    titleRes = R.string.network_error_message_title,
    messageRes = R.string.network_error_message_text,
)