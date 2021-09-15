package com.example.effectiveexpensesmanager.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.adapter.DataAdapter
import com.example.effectiveexpensesmanager.database.DataDAO
import com.example.effectiveexpensesmanager.database.DataModel
import com.example.effectiveexpensesmanager.database.DataRoomDataBase
import com.example.effectiveexpensesmanager.database.onItemClickListener
import kotlinx.android.synthetic.main.fragment_income.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeFragment : Fragment(R.layout.fragment_income), onItemClickListener {

    private lateinit var dataAdapter: DataAdapter
    private var dataList = mutableListOf<DataModel>()

    private lateinit var roomDb : DataRoomDataBase
    private lateinit var dataDAO: DataDAO

    override fun onAttach(context: Context) {
        super.onAttach(context)
        roomDb = DataRoomDataBase.getDataBaseObject(context)
        dataDAO = roomDb.getDataDAO()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataDAO.getAllIncomeData().observe(viewLifecycleOwner, {
            val datas = it
            val total_expense=0
            dataList.clear()
            dataList.addAll(datas)
            Log.d("TAG", "onViewCreated: $total_expense")
            dataAdapter.notifyDataSetChanged()
        })

        dataAdapter = DataAdapter(context as Activity ,dataList,this)
        recyclerview_income.adapter = dataAdapter

    }

    override fun onEditClicked(dataModel: DataModel) {
        val newDate = "21/09/21"
        val newDesc = "This is new Income"
        val newAmount = 12500
        val newcategory = "Income"
        dataModel.amount = newAmount
        dataModel.desc = newDesc
        dataModel.date = newDate
        dataModel.category = newcategory

        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.updateData(dataModel)
        }
    }

    override fun onDeleteClicked(dataModel: DataModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.deleteData(dataModel)
        }
    }

}

