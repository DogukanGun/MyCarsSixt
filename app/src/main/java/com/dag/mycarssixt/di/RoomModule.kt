package com.dag.mycarssixt.di

import android.content.Context
import com.dag.mycarssixt.network.localrepo.FavCarDao
import com.dag.mycarssixt.network.localrepo.FavCarRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideFavCarDao(
        @ApplicationContext context: Context
    ):FavCarDao = FavCarRoomDatabase.getDatabase(context).favCarDao()

}