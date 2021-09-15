package com.example.effectiveexpensesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.effectiveexpensesmanager.database.DataDAO
import com.example.effectiveexpensesmanager.database.DataModel
import com.example.effectiveexpensesmanager.database.DataRoomDataBase
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddDataActivity : AppCompatActivity() {

    lateinit var roomDb: DataRoomDataBase
    lateinit var dataDAO: DataDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        roomDb = DataRoomDataBase.getDataBaseObject(this)
        dataDAO = roomDb.getDataDAO()

        btnAddData.setOnClickListener {
            var amount = etInputAmt.text.toString().toInt()
            var desc = etInputDesc.text.toString()
            var currentDate = etInputDate.text.toString()
            var category = spinner.selectedItem.toString()

            val dataModel = DataModel(category,amount,desc,currentDate)

            CoroutineScope(Dispatchers.IO).launch {
                dataDAO.addData(dataModel)
            }
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}