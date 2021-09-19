package com.example.effectiveexpensesmanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.effectiveexpensesmanager.models.DataModel
import com.example.effectiveexpensesmanager.repository.DataRepo

class DataViewModel(val repo: DataRepo): ViewModel() {

    fun addData(dataModel: DataModel){
        repo.addDataToRoom(dataModel)
    }

    fun getAllIncomeData(): LiveData<List<DataModel>>{
        return repo.getAllIncomeData()
    }
    fun getAllExpenseData(): LiveData<List<DataModel>>{
        return repo.getAllExpenseData()
    }

    fun updateData(dataModel: DataModel){
        repo.updateData(dataModel)
    }

    fun deleteData(dataModel: DataModel){
        repo.deleteData(dataModel)
    }
}