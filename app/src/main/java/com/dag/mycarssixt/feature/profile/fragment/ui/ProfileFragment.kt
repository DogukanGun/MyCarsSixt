package com.dag.mycarssixt.feature.profile.fragment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dag.mycarssixt.R
import com.dag.mycarssixt.base.adapter.ListItem
import com.dag.mycarssixt.base.adapter.listAdapter
import com.dag.mycarssixt.base.ui.MyCarsSixtFragment
import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import com.dag.mycarssixt.databinding.FragmentProfileBinding
import com.dag.mycarssixt.network.localrepo.FavCar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment:MyCarsSixtFragment<ProfileFragmentVM,FragmentProfileBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun getLayoutVM(): ProfileFragmentVM = profileFragmentVM

    @Inject
    lateinit var profileFragmentVM: ProfileFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.getAllCars()
        return view
    }

    override fun handleState(viewState: MyCarsSixtViewState) {
        super.handleState(viewState)
        when(viewState){
            is ProfileFragmentState.FavCars ->{
                createRecyclerView(viewState.favCars)
            }
        }
    }

    private fun createRecyclerView(favCars:List<FavCar>){
        val itemList = mutableListOf<ProfileRowItem>()
        itemList.add(ProfileRowItem(R.layout.item_profile_user))
        for (favCar in favCars){
            itemList.add(ProfileRowItem(R.layout.item_profile_favcar,favCar))
        }
        val recyclerViewAdapter = listAdapter {
            list = itemList.toMutableList()
        }
        binding.profileListRV.adapter = recyclerViewAdapter
        binding.profileListRV.layoutManager = LinearLayoutManager(requireContext())
    }
}