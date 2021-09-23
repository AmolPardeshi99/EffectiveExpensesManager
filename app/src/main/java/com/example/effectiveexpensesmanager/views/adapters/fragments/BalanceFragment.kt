package com.example.effectiveexpensesmanager.views.adapters.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.models.roomdb.DataDAO
import com.example.effectiveexpensesmanager.models.roomdb.DataRoomDataBase
import kotlinx.android.synthetic.main.fragment_balance.*

class BalanceFragment : Fragment(R.layout.fragment_balance) {


    lateinit var roomDb: DataRoomDataBase
    lateinit var dataDAO: DataDAO
    var total_expense: Int = 0
    var total_Income: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        roomDb = DataRoomDataBase.getDataBaseObject(context)
        dataDAO = roomDb.getDataDAO()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataDAO.getAllIncomeData().observe(viewLifecycleOwner, {
            val datas = it
            total_Income=0
            datas.forEach {
                if (it.category == "Income") total_Income += it.amount
            }

        })
        //
        dataDAO.getAllExpenseData().observe(viewLifecycleOwner, {
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