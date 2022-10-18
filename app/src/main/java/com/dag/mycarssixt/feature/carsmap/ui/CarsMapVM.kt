package com.dag.mycarssixt.feature.carsmap.ui

import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.domain.None
import com.dag.mycarssixt.base.ui.MyCarsSixtViewModel
import com.dag.mycarssixt.component.toolbar.MyCarsSixtToolbarData
import com.dag.mycarssixt.feature.carsmap.data.CarMarker
import com.dag.mycarssixt.feature.carsmap.domain.GetCarsMapperUseCase
import javax.inject.Inject

class CarsMapVM @Inject constructor(
    private val getCarsMapperUseCase: GetCarsMapperUseCase
): MyCarsSixtViewModel() {

    init {
        setMyCarsSixtToolbarViewData(
            MyCarsSixtToolbarData(
                leftImageRes = R.drawable.ic_baseline_back,
                fullyCenteredTitleRes = R.string.carsmap_title,
                rightImageRes = null
            )
        )
    }

    fun getCarMarkers(){
        getCarsMapperUseCase.observe(None)
            .publishLoading()
            .subscribe {
                val carMarkersList = it?.map { car->
                    CarMarker(
                        group = car.group,
                        series = car.series,
                        latitude = car.latitude,
                        longitude = car.longitude,
                        licensePlate = car.licensePlate
                    )
                } ?: emptyList()
                viewState.postValue(
                    CarsMapViewState.Cars(
                        carMarkersList
                    )
                )
            }
    }
}