package com.dag.mycarssixt.network

import retrofit2.http.POST

interface SixtService {

    @POST("")
    fun getCars()
}