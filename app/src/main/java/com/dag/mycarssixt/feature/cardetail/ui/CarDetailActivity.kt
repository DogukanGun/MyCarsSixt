package com.dag.mycarssixt.feature.cardetail.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.adapter.basicAdapter
import com.dag.mycarssixt.base.ui.MyCarsSixtActivity
import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.databinding.ActivityCardetailBinding
import com.dag.mycarssixt.feature.cardetail.data.KeyValuePair
import com.dag.mycarssixt.feature.cars.data.Car
import com.dag.mycarssixt.network.localrepo.FavCar
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
            viewModel.getCarLikeStatus(FavCar(
                carModel = it.modelName,
                carId = it.id,
                liked = false
            ))
            createUI(it)
        }
        binding.toolbar.apply {
            onRightImageClick = {
                like()
            }
            onLeftImageClick = {
                finish()
            }
        }
    }

    override fun handleState(viewState: MyCarsSixtViewState) {
        super.handleState(viewState)
        when(viewState){
            is CarDetailState.CarLikeStatus->{
              setCarStatus(viewState.favCar)
            }
        }
    }

    private fun like(){
        viewModel.like()
    }

    private fun setCarStatus(favCar: FavCar){
        if (favCar.liked){
            ContextCompat.getDrawable(this,R.drawable.ic_baseline_liked)?.let {
                binding.toolbar.setRightImageImage(it)
            }
        }else{
            ContextCompat.getDrawable(this,R.drawable.ic_baseline_not_liked)?.let {
                binding.toolbar.setRightImageImage(it)
            }
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