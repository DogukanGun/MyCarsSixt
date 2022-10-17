package com.dag.mycarssixt.base.adapter

class ListAdapter<T : ListItem> private constructor(
    itemClickListener: ItemClickListener<T>?,
    selectionEnabled: Boolean,
    private val list: MutableList<T>,
    bindExtra: (viewHolder:BasicAdapter<T>.ViewHolder,position:Int)->Unit
) : BasicAdapter<T>(0, itemClickListener, selectionEnabled,list,bindExtra) {

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutId
    }

    override fun getItemId(position: Int) = position.toLong()

    companion object {
        fun <T : ListItem> createWith(builder: Builder<T>): ListAdapter<T> {

            return ListAdapter(
                builder.itemClickListener,
                builder.itemSelectionEnabled,
                builder.list,
                builder.bindExtra
            )
        }
    }
}

