package com.example.effectiveexpensesmanager.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.adapter.DataAdapter
import com.example.effectiveexpensesmanager.adapter.DataModel
import com.example.effectiveexpensesmanager.database.DatabaseHandler
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlin.math.absoluteValue

class BalanceFragment : Fragment() {


    lateinit var dbHandler : DatabaseHandler
    var mutableList = mutableListOf<DataModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHandler = DatabaseHandler(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var expense :Int  = dbHandler.getTotalExpense()
        var income: Int = dbHandler.getTotalIncome()
        var totalBal = income-expense
        tvIncome.text = "Total Income: Rs.${income}"
        tvExpense.text = "Total Expense: Rs.${expense}"
        tvTotal.text = "Total Balance: Rs.${totalBal}"


    }



}