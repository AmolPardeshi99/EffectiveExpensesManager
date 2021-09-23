package com.example.effectiveexpensesmanager.views.adapters.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.views.adapters.adapters.DataAdapter
import com.example.effectiveexpensesmanager.models.roomdb.DataDAO
import com.example.effectiveexpensesmanager.models.roomdb.DataModel
import com.example.effectiveexpensesmanager.models.roomdb.DataRoomDataBase
import com.example.effectiveexpensesmanager.repository.DataRepo
import com.example.effectiveexpensesmanager.viewmodels.DataViewModel
import com.example.effectiveexpensesmanager.viewmodels.DataViewModelFactory
import com.example.effectiveexpensesmanager.views.adapters.onItemClickListener
import kotlinx.android.synthetic.main.fragment_expense.*

class ExpenseFragment : Fragment(R.layout.fragment_expense), onItemClickListener {

    private lateinit var dataAdapter: DataAdapter
    private var dataList = mutableListOf<DataModel>()

    lateinit var roomDb : DataRoomDataBase
    lateinit var dataDAO: DataDAO
    lateinit var dataViewModel: DataViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        roomDb = DataRoomDataBase.getDataBaseObject(context)
        dataDAO = roomDb.getDataDAO()

        val repo = DataRepo(dataDAO)
        val viewModelFactory = DataViewModelFactory(repo)
        dataViewModel = ViewModelProviders.of(this,viewModelFactory).get(DataViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataViewModel.getAllExpenseData().observe(viewLifecycleOwner, {
            dataList.clear()
            dataList.addAll(it)
            dataAdapter.notifyDataSetChanged()
        })

        dataAdapter = DataAdapter(context as Activity ,dataList,this)
        recyclerview_expense.adapter = dataAdapter

    }

    override fun onEditClicked(dataModel: DataModel) {
        dataModel.amount = 125
        dataModel.desc = "This is new Expense"
        dataModel.date = "20/12/21"
        dataModel.category = "Expense"

        dataViewModel.updateData(dataModel)
    }

    override fun onDeleteClicked(dataModel: DataModel) {
       dataViewModel.deleteData(dataModel)
    }


}