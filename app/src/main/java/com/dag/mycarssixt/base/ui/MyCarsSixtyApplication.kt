package com.dag.mycarssixt.base.ui

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyCarsSixtyApplication : MultiDexApplication(){

    companion object {
        lateinit var appInstance: MyCarsSixtyApplication
        var baseUrl = "https://cdn.sixt.io/codingtask/"
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}