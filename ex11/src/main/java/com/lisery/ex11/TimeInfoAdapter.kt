package com.lisery.ex11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lisery.ex11.databinding.RowBinding
import com.lisery.ex11.time_insert.TimeInfo

class TimeInfoAdapter(private val inflater: LayoutInflater) :
    ListAdapter<TimeInfo, TimeViewHolder>(TimeDiffer) {

    private object TimeDiffer : DiffUtil.ItemCallback<TimeInfo>() {
        override fun areItemsTheSame(oldInfo: TimeInfo, newInfo: TimeInfo): Boolean {
            return oldInfo.id == newInfo.id
        }

        override fun areContentsTheSame(oldInfo: TimeInfo, newInfo: TimeInfo): Boolean {
            return areItemsTheSame(oldInfo, newInfo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        return TimeViewHolder(RowBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}