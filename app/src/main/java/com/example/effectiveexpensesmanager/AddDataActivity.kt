package com.example.effectiveexpensesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.effectiveexpensesmanager.database.DatabaseHandler
import kotlinx.android.synthetic.main.activity_add_data.*
import java.text.SimpleDateFormat
import java.util.*

class AddDataActivity : AppCompatActivity() {
    var dbHandler = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        btnAddData.setOnClickListener {
            var amount = etInputAmt.text.toString().toInt()
            var desc = etInputDesc.text.toString()
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            var currentDate = sdf.format(Date()).toString()
            var category = spinner.selectedItem.toString()

            if (category.equals("Income")){
                dbHandler.insertIncome(amount,desc,currentDate)
            }else{
                dbHandler.insertExpense(amount,desc,currentDate)
            }
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}