package com.dag.mycarssixt.component.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.dag.mycarssixt.BR
import com.dag.mycarssixt.R
import com.dag.mycarssixt.databinding.ComponentMycarssixttoolbarBinding

@BindingAdapter("MyCarsSixtToolbar:setComponentData")
fun MyCarsSixtToolbar.setComponentData(viewData: MyCarsSixtToolbarData?) {
    viewModel.setData(viewData)
}

typealias TextOrRes = Pair<CharSequence?, Int?>

fun TextOrRes.getString(context: Context): String? {
    return when {
        first != null -> first?.toString()
        second != null && second != 0 -> context.getString(second!!)
        else -> null
    }
}

class MyCarsSixtToolbar: ConstraintLayout {

    var onLeftImageClick: (() -> Unit)? = null
    var onRightImageClick: (() -> Unit)? = null
    var myCarsSixtToolbarData = MyCarsSixtToolbarData()

    val viewModel = MyCarsSixtToolbarVM()

    private lateinit var binding:ComponentMycarssixttoolbarBinding

    constructor(context: Context) : super(context){
        initLayout(null,0)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initLayout(attrs,0)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initLayout(attrs,defStyleAttr)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        initLayout(attrs,defStyleAttr)
    }

    private fun initLayout(attrs: AttributeSet?, defStyle: Int){
        binding =  DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.component_mycarssixttoolbar,
            this,
            true
        )
        viewModel.setData(myCarsSixtToolbarData)
        binding.imageLeftIB.setOnClickListener { onLeftImageClick?.invoke() }
        binding.imageRightIB.setOnClickListener { onRightImageClick?.invoke() }
        binding.setVariable(BR.viewModel,viewModel)
    }
}