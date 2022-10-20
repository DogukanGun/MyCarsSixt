package com.dag.mycarssixt.feature.profile.activity

import android.os.Bundle
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.ui.MyCarsSixtActivity
import com.dag.mycarssixt.databinding.ActivityProfileBinding
import com.dag.mycarssixt.feature.profile.fragment.ui.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity: MyCarsSixtActivity<ProfileActivityVM,ActivityProfileBinding>() {

    override fun getMyCarsSixtViewModel(): ProfileActivityVM = profileActivityVM

    override fun getLayout(): Int = R.layout.activity_profile

    @Inject
    lateinit var profileActivityVM: ProfileActivityVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(ProfileFragment())
        binding.toolbar.onLeftImageClick = {
            finish()
        }
    }

}