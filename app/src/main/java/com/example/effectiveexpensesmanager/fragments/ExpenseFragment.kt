package com.example.effectiveexpensesmanager.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.adapter.DataAdapter
import com.example.effectiveexpensesmanager.database.DataDAO
import com.example.effectiveexpensesmanager.database.DataModel
import com.example.effectiveexpensesmanager.database.DataRoomDataBase
import com.example.effectiveexpensesmanager.database.onItemClickListener
import kotlinx.android.synthetic.main.fragment_expense.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseFragment : Fragment(R.layout.fragment_expense), onItemClickListener{

    private lateinit var dataAdapter: DataAdapter
    private var dataList = mutableListOf<DataModel>()

    lateinit var roomDb : DataRoomDataBase
    lateinit var dataDAO: DataDAO


    override fun onAttach(context: Context) {
        super.onAttach(context)
        roomDb = DataRoomDataBase.getDataBaseObject(context)
        dataDAO = roomDb.getDataDAO()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataAdapter = DataAdapter(context as Activity ,dataList,this)
        recyclerview_expense.adapter = dataAdapter

        dataDAO.getAllExpenseData().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val datas = it
            dataList.clear()
            dataList.addAll(datas)
            dataAdapter.notifyDataSetChanged()
        })


    }

    override fun onEditClicked(dataModel: DataModel) {
        dataModel.amount = 125
        dataModel.desc = "This is new Expense"
        dataModel.date = "20/12/21"
        dataModel.category = "Expense"

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