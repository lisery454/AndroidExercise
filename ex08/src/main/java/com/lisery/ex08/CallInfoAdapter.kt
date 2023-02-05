package com.lisery.ex08

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.lisery.ex08.databinding.RowBinding

class CallInfoAdapter(context: Context, resID: Int, data: List<CallInfo>) :
    ArrayAdapter<CallInfo>(context, resID, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = RowBinding.inflate(LayoutInflater.from(context))
        binding.name.text = getItem(position)?.name ?: "???"
        binding.number.text = getItem(position)?.number ?: "???"

        return binding.root
    }
}