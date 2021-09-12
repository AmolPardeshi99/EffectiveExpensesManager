package com.example.effectiveexpensesmanager.database

import com.example.effectiveexpensesmanager.adapter.DataModel

interface onItemClickListener {

    fun onEditClicked_Expense(dataModel: DataModel)

    fun onDeleteClicked_Expense(dataModel: DataModel)

    fun onEditClicked_Income(dataModel: DataModel)

    fun onDeleteClicked_Income(dataModel: DataModel)
}