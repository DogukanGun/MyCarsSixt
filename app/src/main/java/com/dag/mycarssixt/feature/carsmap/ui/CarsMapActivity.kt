package com.dag.mycarssixt.feature.carsmap.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ext.vectorToBitmap
import com.dag.mycarssixt.base.ui.MyCarsSixtActivity
import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.databinding.ActivityCarsmapBinding
import com.dag.mycarssixt.feature.carsmap.data.CarMarker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarsMapActivity: MyCarsSixtActivity<CarsMapVM,ActivityCarsmapBinding>() {

    override fun getHomeViewModel(): CarsMapVM = carsMapVM

    override fun getLayout(): Int = R.layout.activity_carsmap

    @Inject
    lateinit var carsMapVM: CarsMapVM

    var mapFragment: SupportMapFragment? = null
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
                createMapMarkers(viewState.carsMarkerList)
            }
        }
    }

    private fun createMapMarkers(places:List<CarMarker>){
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap->
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                places.forEach { bounds.include(it.latLong) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
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
            marker?.tag = place
        }
    }

}