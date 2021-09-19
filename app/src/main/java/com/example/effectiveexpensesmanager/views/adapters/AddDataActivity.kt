package com.example.effectiveexpensesmanager.views.adapters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.models.DataDAO
import com.example.effectiveexpensesmanager.models.DataModel
import com.example.effectiveexpensesmanager.models.DataRoomDataBase
import com.example.effectiveexpensesmanager.repository.DataRepo
import com.example.effectiveexpensesmanager.viewmodels.DataViewModel
import com.example.effectiveexpensesmanager.viewmodels.DataViewModelFactory
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDataActivity : AppCompatActivity() {

    lateinit var roomDb: DataRoomDataBase
    lateinit var dataDAO: DataDAO

    lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        roomDb = DataRoomDataBase.getDataBaseObject(this)
        dataDAO = roomDb.getDataDAO()

        val repo = DataRepo(dataDAO)
        val dataViewModelFactory = DataViewModelFactory(repo)
        dataViewModel = ViewModelProviders.of(this,dataViewModelFactory).get(DataViewModel::class.java)

        btnAddData.setOnClickListener {
            var amount = etInputAmt.text.toString().toInt()
            var desc = etInputDesc.text.toString()
            var currentDate = etInputDate.text.toString()
            var category = spinner.selectedItem.toString()

            val dataModel = DataModel(category,amount,desc,currentDate)
            dataViewModel.addData(dataModel)

            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}