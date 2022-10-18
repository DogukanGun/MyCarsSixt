package com.dag.mycarssixt.feature.carsmap.ui

import android.os.Bundle
import android.view.View
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ui.MyCarsSixtActivity
import com.dag.mycarssixt.databinding.ActivityCarsmapBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarsMapActivity: MyCarsSixtActivity<CarsMapVM,ActivityCarsmapBinding>() {

    override fun getHomeViewModel(): CarsMapVM = carsMapVM

    override fun getLayout(): Int = R.layout.activity_carsmap

    @Inject
    lateinit var carsMapVM: CarsMapVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.onLeftImageClick = {
            this.finish()
        }
    }
}