package com.dag.mycarssixt.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dag.mycarssixt.BR
import com.dag.mycarssixt.R
import com.dag.mycarssixt.network.dialogbox.ModelDialog
import com.dag.mycarssixt.network.dialogbox.ModelDialogHandler
import javax.inject.Inject

abstract class MyCarsSixtActivity<VM : MyCarsSixtViewModel, VB : ViewDataBinding> :
    AppCompatActivity() {

    lateinit var binding: VB
    lateinit var viewModel: VM

    abstract fun getMyCarsSixtViewModel(): VM
    abstract fun getLayout(): Int
    private fun getContainerId() = R.id.container

    @Inject
    lateinit var modelDialogHandler: ModelDialogHandler

    @Inject
    lateinit var myCarsSixtProgressDialogManager: MyCarsSixtProgressDialogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getMyCarsSixtViewModel()
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.setVariable(BR.viewModel, viewModel)
        if (!viewModel.viewState.hasActiveObservers()) {
            viewModel.viewState.observe(this) {
                handleState(it)
            }
        }
        if (!viewModel.isLoading().hasActiveObservers()) {
            viewModel.isLoading().observe(this) {
                handleLoadingState(it)
            }
        }
    }

    open fun handleState(viewState: MyCarsSixtViewState) {
        when (viewState) {
            is ModelDialog -> modelDialogHandler.showDialog(this, viewState)
        }
    }

    fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            myCarsSixtProgressDialogManager.showLoadingDialog(this)
        } else {
            myCarsSixtProgressDialogManager.stopDialog()
        }
    }

    fun addFragment(fragment:MyCarsSixtFragment<*,*>){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(getContainerId(),fragment,null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.last() is MyCarsSixtFragment<*,*>) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}