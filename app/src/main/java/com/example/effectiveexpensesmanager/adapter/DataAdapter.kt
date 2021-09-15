package com.example.effectiveexpensesmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.database.DataModel
import com.example.effectiveexpensesmanager.database.onItemClickListener

class DataAdapter(val context: Context, val list : MutableList<DataModel>, var listener: onItemClickListener): RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false),listener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setData(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
}