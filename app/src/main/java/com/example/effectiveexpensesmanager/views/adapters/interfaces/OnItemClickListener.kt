package com.example.effectiveexpensesmanager.views.adapters

import com.example.effectiveexpensesmanager.models.roomdb.DataModel

interface onItemClickListener {

    fun onEditClicked(dataModel: DataModel)

    fun onDeleteClicked(dataModel: DataModel)

}