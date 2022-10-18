package com.dag.mycarssixt.component.toolbar

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dag.mycarssixt.R
import javax.inject.Inject

class MyCarsSixtToolbarVM: ViewModel() {

    val leftImageResObservable = ObservableInt()
    val leftImageVisibilityObservable = ObservableBoolean()
    val rightImageResObservable = ObservableInt()
    val rightImageVisibilityObservable = ObservableBoolean()
    val fullyCenteredTitleObservable = ObservableField<TextOrRes?>()

    fun setData(viewData:MyCarsSixtToolbarData?){
        viewData?.run {
            leftImageResObservable.set(leftImageRes ?: 0)
            leftImageVisibilityObservable.set(imageShouldVisible(leftImageRes))
            rightImageResObservable.set(rightImageRes ?: 0)
            rightImageVisibilityObservable.set(imageShouldVisible(rightImageRes))
            fullyCenteredTitleObservable.set(TextOrRes(fullyCenteredTitle, fullyCenteredTitleRes))
        }
    }

    private fun imageShouldVisible(res: Int?) = res != null && res != 0

}

