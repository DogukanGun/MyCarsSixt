package com.dag.mycarssixt.base.ext

import android.content.Context
import android.net.ConnectivityManager
import java.lang.Exception
import java.net.NetworkInterface
import java.util.*

fun Context.isNetworkStatusAvailable():Boolean{
    val connectivityManager = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (netInfo.isConnected) return true
        }
    }
    return false
}
