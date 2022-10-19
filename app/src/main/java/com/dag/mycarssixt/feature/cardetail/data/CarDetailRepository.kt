package com.dag.mycarssixt.feature.cardetail.data

import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.network.BaseRepository
import com.dag.mycarssixt.network.BaseResult
import com.dag.mycarssixt.network.localrepo.FavCar
import com.dag.mycarssixt.network.localrepo.FavCarDao
import javax.inject.Inject

class CarDetailRepository @Inject constructor(
    private val favCarDao: FavCarDao
): BaseRepository() {
    fun likeFavCar(carId:String) = fetch{
        favCarDao.likeTheCar(carId)
        BaseResult.Success(None)
    }
    fun dislikeFavCar(carId: String) = fetch{
        favCarDao.dislikeTheCar(carId)
        BaseResult.Success(None)
    }
    fun getFavCar(carId: String) = fetch{
        BaseResult.Success(favCarDao.getItem(carId))
    }
    fun addFavCar(favCar: FavCar) = fetch {
        favCarDao.insert(favCar)
        BaseResult.Success(None)
    }
}