package com.example.effectiveexpensesmanager.views.adapters.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.models.roomdb.DataDAO
import com.example.effectiveexpensesmanager.models.roomdb.DataRoomDataBase
import com.example.effectiveexpensesmanager.repository.DataRepo
import com.example.effectiveexpensesmanager.viewmodels.DataViewModel
import com.example.effectiveexpensesmanager.viewmodels.DataViewModelFactory
import kotlinx.android.synthetic.main.fragment_balance.*

class BalanceFragment : Fragment(R.layout.fragment_balance) {


    lateinit var roomDb: DataRoomDataBase
    lateinit var dataDAO: DataDAO
    lateinit var dataViewModel: DataViewModel
    var total_expense: Int = 0
    var total_Income: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        roomDb = DataRoomDataBase.getDataBaseObject(context)
        dataDAO = roomDb.getDataDAO()

        val repo = DataRepo(dataDAO)
        val factory = DataViewModelFactory(repo)
        dataViewModel = ViewModelProviders.of(this,factory).get(DataViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataViewModel.getAllIncomeData().observe(viewLifecycleOwner, {
            val datas = it
            total_Income=0
            datas.forEach {
                if (it.category == "Income") total_Income += it.amount
            }

        })
        //
        dataViewModel.getAllExpenseData().observe(viewLifecycleOwner, {
            val datas = it
            total_expense = 0
            datas.forEach {
                if (it.category == "Expense") total_expense += it.amount
            }
            tvExpense.text = "Total Expense: "+total_expense
            tvIncome.text = "Total Income: "+total_Income
            val balance = total_Income - total_expense
            tvTotal.text = "Available Balance: "+balance
        })


    }


}