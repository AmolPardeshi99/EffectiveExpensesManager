package com.example.effectiveexpensesmanager.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.adapter.DataAdapter
import com.example.effectiveexpensesmanager.adapter.DataModel
import com.example.effectiveexpensesmanager.database.DatabaseHandler
import com.example.effectiveexpensesmanager.database.onItemClickListener
import kotlinx.android.synthetic.main.fragment_income.*
import kotlinx.android.synthetic.main.item_layout.*
import java.lang.Exception
import java.util.ArrayList

class IncomeFragment : Fragment(), onItemClickListener {

    private lateinit var dataAdapter: DataAdapter
    private var mutableList = mutableListOf<DataModel>()
    private var dbHandler : DatabaseHandler? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHandler = DatabaseHandler(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            mutableList = dbHandler?.getIncomeData()!!
        }catch (e: Exception){
            Toast.makeText(activity, "Data is not Available", Toast.LENGTH_SHORT).show()
        }
        setRecyclerview()

//        btnDelete.setOnClickListener {
//
//        }
//
//        btnEdit.setOnClickListener {
//
//        }
    }

    private fun setRecyclerview() {

        dataAdapter = DataAdapter(context as Activity ,mutableList,this)
        recyclerview_income.adapter = dataAdapter
    }


    override fun onEditClicked_Expense(dataModel: DataModel) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClicked_Expense(dataModel: DataModel) {
        TODO("Not yet implemented")
    }

    override fun onEditClicked_Income(dataModel: DataModel) {

    }

    override fun onDeleteClicked_Income(dataModel: DataModel) {
        TODO("Not yet implemented")
    }

    private fun updateUI(){
        val  latestData = dbHandler?.getIncomeData()
        mutableList.clear()
        latestData?.let { mutableList.addAll(it) }
        dataAdapter.notifyDataSetChanged()
    }


}