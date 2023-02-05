package com.lisery.ex11

import androidx.recyclerview.widget.RecyclerView
import com.lisery.ex11.databinding.RowBinding
import com.lisery.ex11.time_insert.TimeInfo

class TimeViewHolder(private val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(timeInfo: TimeInfo) {
        binding.idCol.text = timeInfo.id.toString()
        binding.timeCol.text = timeInfo.time
    }
}