package com.example.effectiveexpensesmanager.views.adapters.fragments

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.fragment_income.*

class IncomeFragment : Fragment(R.layout.fragment_income), onItemClickListener {

    private lateinit var dataAdapter: DataAdapter
    private var dataList = mutableListOf<DataModel>()

    private lateinit var roomDb : DataRoomDataBase
    private lateinit var dataDAO: DataDAO
    lateinit var viewModel: DataViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        roomDb = DataRoomDataBase.getDataBaseObject(context)
        dataDAO = roomDb.getDataDAO()

        val repo = DataRepo(dataDAO)
        val viewModelFactory = DataViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(DataViewModel::class.java)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllIncomeData().observe(viewLifecycleOwner, {
            dataList.clear()
            dataList.addAll(it)
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

        viewModel.updateData(dataModel)
    }

    override fun onDeleteClicked(dataModel: DataModel) {
        viewModel.deleteData(dataModel)
    }

}

