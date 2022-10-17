package com.dag.mycarssixt.errorhandling

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState


interface ErrorHandler {
    fun handle(throwable: Throwable): MyCarsSixtViewState?
}