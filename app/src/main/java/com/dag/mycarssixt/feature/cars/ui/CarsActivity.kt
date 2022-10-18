package com.dag.mycarssixt.feature.cars.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.adapter.ItemClickListener
import com.dag.mycarssixt.base.adapter.basicAdapter
import com.dag.mycarssixt.base.ui.MyCarsSixtActivity
import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.databinding.ActivityCarsBinding
import com.dag.mycarssixt.databinding.ItemCarBinding
import com.dag.mycarssixt.feature.cars.data.Car
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarsActivity: MyCarsSixtActivity<CarsVM,ActivityCarsBinding>() {

    override fun getHomeViewModel(): CarsVM = carsVM

    override fun getLayout(): Int = R.layout.activity_cars

    @Inject
    lateinit var carsVM: CarsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carsVM.getCars()
    }

    override fun handleState(viewState: MyCarsSixtViewState) {
        super.handleState(viewState)
        when(viewState){
            is CarsState.Cars ->{
                createAdapter(viewState.carsList)
            }
        }
    }

    private fun createAdapter(cars:List<Car>){
        val recyclerViewAdapter = basicAdapter<Car> {
            itemLayoutId = R.layout.item_car
            itemClickListener = carRecyclerViewItemClickListener
            list = cars.toMutableList()
            imageEnable=true
        }
        binding.carsListRV.adapter = recyclerViewAdapter
        binding.carsListRV.layoutManager = LinearLayoutManager(this)
    }

    private val carRecyclerViewItemClickListener = ItemClickListener<Car>{ position, item ->

    }

}