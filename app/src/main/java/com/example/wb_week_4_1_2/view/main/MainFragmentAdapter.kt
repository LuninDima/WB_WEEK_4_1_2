package com.example.wb_week_4_1_2.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_week_4_1_2.R
import com.example.wb_week_4_1_2.model.Chat
import com.example.wb_week_4_1_2.utils.DiffUtils

class MainFragmentAdapter(
    private var onItemViewClickListener:
    MainFragment.OnItemViewClickListener?
) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

        private var chatData = emptyList<Chat>()

    private lateinit var mDiffResult: DiffUtil.DiffResult

    fun setChatData(data: List<Chat>) {
         val chatListData = chatData.toMutableList()
        chatListData.clear()
        mDiffResult = DiffUtil.calculateDiff(DiffUtils(data, chatListData))
        chatData = data
        mDiffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false)
                    as View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(chatData[position])
    }

    override fun getItemCount(): Int {
        return chatData.size
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(chatData: Chat) {
            itemView.apply {
                findViewById<ImageView>(R.id.ivChat).setImageResource(chatData.image)
                findViewById<TextView>(R.id.tvTitleChat).text =
                    chatData.title
                findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                    chatData.chatTexts[0].text
                findViewById<TextView>(R.id.tvTime).text =
                    chatData.timeLastMessage
                findViewById<TextView>(R.id.countChatMessengs).text =
                    chatData.countMessage.toString()
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(chatData)
                }
            }

        }
    }
}
