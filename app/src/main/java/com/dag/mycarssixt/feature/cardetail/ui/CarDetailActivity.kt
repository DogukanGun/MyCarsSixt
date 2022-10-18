package com.dag.mycarssixt.feature.cardetail.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.adapter.basicAdapter
import com.dag.mycarssixt.base.ui.MyCarsSixtActivity
import com.dag.mycarssixt.databinding.ActivityCardetailBinding
import com.dag.mycarssixt.feature.cardetail.data.KeyValuePair
import com.dag.mycarssixt.feature.cars.data.Car
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

@AndroidEntryPoint
class CarDetailActivity: MyCarsSixtActivity<CarDetailVM,ActivityCardetailBinding>(){

    override fun getMyCarsSixtViewModel(): CarDetailVM = carDetailVM

    override fun getLayout(): Int = R.layout.activity_cardetail

    @Inject
    lateinit var carDetailVM: CarDetailVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<Car>(carKey)?.let {
            viewModel.changeToolbar(it.name.uppercase().plus("\'s Car"))
            createUI(it)
        }
        binding.toolbar.onLeftImageClick = {
            finish()
        }
    }

    private fun createUI(car:Car){
        uploadImage(car.carImageUrl)
        uploadFeatures(car)
    }

    private fun uploadFeatures(car:Car){
        val keyValueList = mutableListOf<KeyValuePair>()
        for (prop in Car::class.memberProperties) {
            if (prop.name != "carImageUrl"){
                val keyValuePair = KeyValuePair(
                    key = prop.name.replaceFirstChar { it.uppercase() },
                    value = prop.get(car) ?: "Unknown"
                )
                keyValueList.add(keyValuePair)
            }
        }
        binding.carsFeatureListRV.adapter = basicAdapter<KeyValuePair> {
            itemLayoutId = R.layout.item_cardetail
            list = keyValueList
            itemSelectionEnabled = false
        }
        binding.carsFeatureListRV.layoutManager = LinearLayoutManager(this)
    }

    private fun uploadImage(imageUrl:String){
        val options: RequestOptions = RequestOptions()
            .error(R.drawable.car_placeholder)
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .apply(options)
            .into(binding.carImageIV)
    }

    companion object{
        const val carKey = "CAR"
    }
}