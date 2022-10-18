package com.dag.mycarssixt.feature.carsmap.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.dag.mycarssixt.R
import com.dag.mycarssixt.feature.carsmap.data.CarMarker
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CarsMapMapperWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker): View? {
        val place = p0.tag as? CarMarker ?: return null
        val view = LayoutInflater.from(context).inflate(
            R.layout.component_map_marker, null
        )
        view.findViewById<TextView>(
            R.id.carNameTV
        ).text = place.group.plus(",").plus(place.series)
        view.findViewById<TextView>(
            R.id.carDetailTV
        ).text = place.licensePlate
        return view
    }

    override fun getInfoWindow(p0: Marker): View? = null
}