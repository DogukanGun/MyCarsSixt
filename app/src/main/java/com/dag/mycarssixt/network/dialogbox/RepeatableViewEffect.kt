package com.dag.mycarssixt.network.dialogbox

import com.dag.mycarssixt.base.ui.MyCarsSixtViewState
import kotlinx.coroutines.suspendCancellableCoroutine

interface RepeatableViewEffect: MyCarsSixtViewState {
    var onRepeatAction:((Boolean)->Unit)?
}

suspend fun MyCarsSixtViewState.shouldRetry():Boolean = suspendCancellableCoroutine { cancellableContinuation ->
    if (this is RepeatableViewEffect){
        onRepeatAction = {
            cancellableContinuation.resume(it,{})
        }
    }else {
        cancellableContinuation.resume(false,{})
    }
}