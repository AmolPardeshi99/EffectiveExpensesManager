package com.example.effectiveexpensesmanager.database

import com.example.effectiveexpensesmanager.adapter.DataModel

interface onItemClickListener {

    fun onEditClicked(dataModel: DataModel)

    fun onDeleteClicked(dataModel: DataModel)

}