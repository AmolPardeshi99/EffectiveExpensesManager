package com.example.effectiveexpensesmanager.views.adapters.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.effectiveexpensesmanager.models.roomdb.DataModel
import com.example.effectiveexpensesmanager.views.adapters.onItemClickListener
import kotlinx.android.synthetic.main.item_layout.view.*

class DataViewHolder(itemView: View,var listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

    fun setData(dataModel: DataModel){
        itemView.apply {
            tvAmount.text = dataModel.amount.toString()
            tvDate.text = dataModel.date
            tvDesc.text = dataModel.desc

            btnDelete.setOnClickListener{
                listener.onDeleteClicked(dataModel)
            }

            btnEdit.setOnClickListener{
                listener.onEditClicked(dataModel)
            }
        }


    }

}