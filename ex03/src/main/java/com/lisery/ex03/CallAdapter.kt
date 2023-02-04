package com.lisery.ex03

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lisery.ex03.databinding.RowBinding

class CallAdapter(private val inflater: LayoutInflater) :
    ListAdapter<CallInfo, CallViewHolder>(CallDiffer) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CallViewHolder {
        return CallViewHolder(RowBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    private object CallDiffer : DiffUtil.ItemCallback<CallInfo>() {
        override fun areItemsTheSame(oldInfo: CallInfo, newInfo: CallInfo): Boolean {
            return oldInfo.number == newInfo.number
        }

        override fun areContentsTheSame(oldInfo: CallInfo, newInfo: CallInfo): Boolean {
            return areItemsTheSame(oldInfo, newInfo)
        }
    }
}