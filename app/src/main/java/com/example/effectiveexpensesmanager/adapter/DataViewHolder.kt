package com.example.effectiveexpensesmanager.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(dataModel: DataModel){
        itemView.apply {
            tvAmount.text = dataModel.amount.toString()
            tvDate.text = dataModel.date
            tvDesc.text = dataModel.desc
        }
    }

}