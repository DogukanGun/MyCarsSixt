package com.dag.mycarssixt.feature.carsmap.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ext.openActivity
import com.dag.mycarssixt.base.ext.vectorToBitmap
import com.dag.mycarssixt.base.ui.MyCarsSixtActivity
import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.databinding.ActivityCarsmapBinding
import com.dag.mycarssixt.feature.cardetail.ui.CarDetailActivity
import com.dag.mycarssixt.feature.cars.data.Car
import com.dag.mycarssixt.feature.carsmap.data.CarMarker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarsMapActivity: MyCarsSixtActivity<CarsMapVM,ActivityCarsmapBinding>() {

    override fun getMyCarsSixtViewModel(): CarsMapVM = carsMapVM

    override fun getLayout(): Int = R.layout.activity_carsmap

    @Inject
    lateinit var carsMapVM: CarsMapVM

    private var mapFragment: SupportMapFragment? = null
    private var carsList:List<Car>? = null
    private val carIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.light_blue_400)
        vectorToBitmap(this, R.drawable.ic_baseline_car, color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.onLeftImageClick = {
            this.finish()
        }
        viewModel.getCarMarkers()

    }

    override fun handleState(viewState: MyCarsSixtViewState) {
        super.handleState(viewState)
        when(viewState){
            is CarsMapViewState.Cars ->{
                carsList = viewState.carsMarkerList
                createMapMarkers(getCarMarkersList(viewState.carsMarkerList))
            }
        }
    }

    private fun getCarMarkersList(carsList:List<Car>):List<CarMarker> =
        carsList.map { car->
            CarMarker(
                id = car.id,
                group = car.group,
                series = car.series,
                latitude = car.latitude,
                longitude = car.longitude,
                licensePlate = car.licensePlate
            )
        }


    private fun createMapMarkers(places:List<CarMarker>){
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap->
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_json))
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                places.forEach { bounds.include(it.latLong) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
            }
            googleMap.setOnInfoWindowClickListener {
                val carFromMapper = carsList?.firstOrNull { carMarker ->
                    carMarker.id == it.tag
                }
                carFromMapper?.let { car->
                    val intent = Intent(this, CarDetailActivity::class.java)
                    intent.putExtra(CarDetailActivity.carKey,car)
                    openActivity(intent)
                }
            }
            googleMap.setInfoWindowAdapter(CarsMapMapperWindowAdapter(this))
            addMarkers(places,googleMap)
        }
    }

    private fun addMarkers(places:List<CarMarker>,googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.placeName)
                    .position(place.latLong)
                    .icon(carIcon)
            )
            marker?.tag = place.id
        }
    }

}