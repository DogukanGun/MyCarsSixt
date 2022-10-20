package com.dag.mycarssixt.feature.cardetail.ui

import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ui.MyCarsSixtViewModel
import com.dag.mycarssixt.component.toolbar.MyCarsSixtToolbarData
import com.dag.mycarssixt.feature.cardetail.domain.AddFavCarUseCase
import com.dag.mycarssixt.feature.cardetail.domain.DislikeCarUseCase
import com.dag.mycarssixt.feature.cardetail.domain.GetFavCarUseCase
import com.dag.mycarssixt.feature.cardetail.domain.LikeCarUseCase
import com.dag.mycarssixt.feature.cars.data.Car
import com.dag.mycarssixt.network.localrepo.FavCar
import javax.inject.Inject

class CarDetailVM @Inject constructor(
    private val addFavCarUseCase: AddFavCarUseCase,
    private val dislikeCarUseCase: DislikeCarUseCase,
    private val getFavCarUseCase: GetFavCarUseCase,
    private val likeFavCarUseCase: LikeCarUseCase
) : MyCarsSixtViewModel() {

    private var toolbarData = MyCarsSixtToolbarData(
        leftImageRes = R.drawable.ic_baseline_back,
        fullyCenteredTitle = "",
        rightImageRes = R.drawable.ic_baseline_not_liked,
    )
    private var _favCar: FavCar? = null
    private var carNotExist = false

    fun getCarLikeStatus(favCar: FavCar) {
        getFavCarUseCase.observe(GetFavCarUseCase.GetFavCarUseCaseParams(favCar.carId))
            .publishLoading()
            .subscribe {
                if (it != null) {
                    _favCar = it
                    viewState.postValue(
                        CarDetailState.CarLikeStatus(it)
                    )
                } else {
                    carNotExist = true
                    _favCar = favCar
                    viewState.postValue(CarDetailState.CarLikeStatus(favCar))
                }
            }
    }

    private fun addFavCar() {
        addFavCarUseCase.observe(AddFavCarUseCase.AddFavCarUseCaseParams(_favCar!!))
            .publishLoading()
            .subscribe {
                it?.let { viewState.postValue(CarDetailState.CarLikeStatus(_favCar!!)) }
            }
    }

    fun changeToolbar(title: String) {
        toolbarData.fullyCenteredTitle = title
        setMyCarsSixtToolbarViewData(toolbarData)
    }

    fun like() {
        _favCar?.let { favCar->
            if (favCar.liked) {
                dislikeCarUseCase.observe(DislikeCarUseCase.DislikeCarUseCaseParams(favCar.carId))
                    .publishLoading()
                    .subscribe{
                        it?.let {
                            favCar.liked = false
                            viewState.postValue(CarDetailState.CarLikeStatus(favCar))
                        }
                    }
            } else {
                favCar.liked = true
                if (carNotExist) {
                    addFavCar()
                    viewState.postValue(CarDetailState.CarLikeStatus(favCar))
                } else {
                    likeFavCarUseCase.observe(LikeCarUseCase.LikeCarUseCaseParams(favCar.carId))
                        .publishLoading()
                        .subscribe {
                            viewState.postValue(CarDetailState.CarLikeStatus(favCar))
                        }
                }
            }
        }
    }

}