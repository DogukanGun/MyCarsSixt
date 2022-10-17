package com.dag.mycarssixt.network

import retrofit2.Response
import java.io.IOException

sealed class MyCarsSixtFailure : IOException()

object NetworkConnectionFailure : MyCarsSixtFailure()

object UnauthenticatedFailure : MyCarsSixtFailure()

object SocketTimeoutFailure : MyCarsSixtFailure()

data class ApiFailure(val response: Response<*>) : MyCarsSixtFailure()

data class UnexpectedFailure(val throwable: Throwable) : MyCarsSixtFailure()
