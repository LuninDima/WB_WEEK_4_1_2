package com.example.wb_week_4_1_2.view.details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_week_4_1_2.R
import com.example.wb_week_4_1_2.model.ChatTexts


class DetailsFragmentAdapter:
    RecyclerView.Adapter<DetailsFragmentAdapter.DetailsViewHolder>() {

    private var chatData: List<ChatTexts> = listOf()

    fun setChatData(data: List<ChatTexts>) {
        chatData = data
      notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsViewHolder {
        return DetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_details_recycler_item, parent, false)
                    as View
        )
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(chatData[position])
    }

    override fun getItemCount(): Int {
        return chatData.size
    }




    inner class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(chatData: ChatTexts) {
            itemView.apply {
                findViewById<TextView>(R.id.tvTextChat).text =
                    chatData.text
            }

        }
    }
}
