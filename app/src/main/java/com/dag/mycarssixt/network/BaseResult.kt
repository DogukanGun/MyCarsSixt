package com.dag.mycarssixt.network

import java.lang.Exception

sealed class BaseResult<out T>{
    data class Success<out T>(val data:T?): BaseResult<T>()
    data class Error(val exception: Exception): BaseResult<Nothing>()
    object Loading: BaseResult<Nothing>()
}

fun <T> BaseResult<T>.getData():T? = if (this is BaseResult.Success) data else null
