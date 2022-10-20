package com.dag.mycarssixt.feature.profile.fragment.data

import com.dag.mycarssixt.network.BaseRepository
import com.dag.mycarssixt.network.BaseResult
import com.dag.mycarssixt.network.localrepo.FavCar
import com.dag.mycarssixt.network.localrepo.FavCarDao
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val favCarDao: FavCarDao
): BaseRepository(){
    fun getAllCars() = fetch {
        BaseResult.Success(favCarDao.getAllCars())
    }
}