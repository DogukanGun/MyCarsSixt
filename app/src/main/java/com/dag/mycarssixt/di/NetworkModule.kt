package com.dag.mycarssixt.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dag.mycarssixt.base.ui.MyCarsSixtyApplication
import com.dag.mycarssixt.network.BaseNetworkLogger
import com.dag.mycarssixt.network.SixtService
import com.dag.mycarssixt.network.calladapter.NetworkResponseAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(MyCarsSixtyApplication.appInstance).build()
    }

    @Provides
    @Singleton
    fun provideBaseOkHttpClientBuilder(
        cache: Cache,
        chuckerInterceptor: ChuckerInterceptor,
        baseNetworkLogger: BaseNetworkLogger,
        ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(baseNetworkLogger)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(60000L, TimeUnit.MILLISECONDS)
            .readTimeout(60000L, TimeUnit.MILLISECONDS)
            .writeTimeout(60000L, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideSixtService(
        retrofit: Retrofit
    ): SixtService = retrofit.create(SixtService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        baseOkHttpClient: Lazy<OkHttpClient>,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(MyCarsSixtyApplication.baseUrl)
            .callFactoryDelegate(baseOkHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCache(application: Application):Cache{
        val cacheSize = 10*1024*1024*2
        return Cache(application.cacheDir,cacheSize.toLong())
    }

}

private fun Retrofit.Builder.callFactoryDelegate(client: Lazy<OkHttpClient>) = callFactory(
    object : Call.Factory {
        override fun newCall(request: Request) = client.get().newCall(request)
    }
)