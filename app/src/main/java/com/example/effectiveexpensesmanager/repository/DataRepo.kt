package com.example.effectiveexpensesmanager.repository

import androidx.lifecycle.LiveData
import com.example.effectiveexpensesmanager.models.DataDAO
import com.example.effectiveexpensesmanager.models.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepo(private val dataDAO: DataDAO) {

    fun addDataToRoom(dataModel: DataModel){
        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.addData(dataModel)
        }
    }

    fun getAllIncomeData(): LiveData<List<DataModel>> {
        return dataDAO.getAllIncomeData()
    }
    fun getAllExpenseData(): LiveData<List<DataModel>> {
        return dataDAO.getAllExpenseData()
    }

    fun updateData(dataModel: DataModel){
        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.updateData(dataModel)
        }
    }

    fun deleteData(dataModel: DataModel){
        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.deleteData(dataModel)
        }
    }
}