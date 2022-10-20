package com.dag.mycarssixt.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dag.mycarssixt.network.dialogbox.ModelDialog

abstract class MyCarsSixtFragment<VM : MyCarsSixtViewModel, VB : ViewDataBinding> : Fragment() {

    lateinit var binding: VB
    lateinit var viewModel: VM

    abstract fun getLayoutId(): Int
    abstract fun getLayoutVM(): VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = getLayoutVM()
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.let { vm ->
            if (!vm.viewState.hasActiveObservers()) {
                vm.viewState.observe(viewLifecycleOwner, Observer {
                    handleState(it)
                })
            }
        }
        return binding.root
    }

    open fun handleState(viewState: MyCarsSixtViewState) {
        when (viewState) {
            is ModelDialog -> {
                getMyCarsSixtActivity().modelDialogHandler.showDialog(
                    getMyCarsSixtActivity(), viewState
                )
            }
        }
    }

    fun getMyCarsSixtActivity():MyCarsSixtActivity<*,*>{
        return activity as MyCarsSixtActivity<*,*>
    }
}