package com.dag.mycarssixt.network.calladapter

import com.dag.mycarssixt.network.BaseResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResponseAdapter<S:Any>(
    private val successType:Type
) : CallAdapter<S, Call<BaseResult<S>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<BaseResult<S>> {
        return NetworkResponseCall(call)
    }
}