package com.example.wb_week_4_1_2.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.wb_week_4_1_2.model.Chat

class DiffUtils(
    private val oldList: List<Chat>,
    private val newList: List<Chat>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}