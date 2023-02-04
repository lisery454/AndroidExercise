package com.lisery.ex03

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.lisery.ex03.databinding.RowBinding

class CallViewHolder(private val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${binding.name.text} ${binding.number.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun bindTo(callInfo: CallInfo) {
        binding.name.text = callInfo.name
        binding.number.text = callInfo.number
    }
}