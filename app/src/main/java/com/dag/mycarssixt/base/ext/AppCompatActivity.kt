package com.dag.mycarssixt.base.ext

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.dag.mycarssixt.R

fun FragmentActivity.openActivity(
    intent: Intent,
    enterAnim:Int = R.anim.slide_in_left,
    exitAnim:Int = R.anim.slide_out_left
){
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    overridePendingTransition(enterAnim,exitAnim)
}
