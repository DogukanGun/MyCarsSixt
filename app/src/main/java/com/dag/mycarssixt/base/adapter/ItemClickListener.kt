package com.dag.mycarssixt.base.adapter

fun interface ItemClickListener<T> {

    fun onClick(position: Int, item: T)
}
