package com.example.effectiveexpensesmanager.views.adapters.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.models.roomdb.DataDAO
import com.example.effectiveexpensesmanager.models.roomdb.DataRoomDataBase
import com.example.effectiveexpensesmanager.models.remote.network.api.Status
import com.example.effectiveexpensesmanager.models.remote.requests.LoginRequestModel
import com.example.effectiveexpensesmanager.repository.DataRepo
import com.example.effectiveexpensesmanager.viewmodels.DataViewModel
import com.example.effectiveexpensesmanager.viewmodels.DataViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast

class LoginActivity : AppCompatActivity() {

    lateinit var roomDb: DataRoomDataBase
    lateinit var dataDAO: DataDAO

    lateinit var dataViewModel: DataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        roomDb = DataRoomDataBase.getDataBaseObject(this)
        dataDAO = roomDb.getDataDAO()

        val repo = DataRepo(dataDAO)

        val dataViewModelFactory = DataViewModelFactory(repo)
        dataViewModel = ViewModelProviders.of(this,dataViewModelFactory).get(DataViewModel::class.java)

        btnLogin.setOnClickListener {

            val loginRequestModel = LoginRequestModel(
//                userName = "pradeep1706108@gmail.com",
//                password = "dhankhar"
                userName = "pradeep1706108@gmail.com",
                password = "dhankhar"

            )

            dataViewModel.userLogin(loginRequestModel).observe(this, Observer {
            val response = it

            when(response.status){

                Status.SUCCESS ->{
                    val name = response.data?.user?.name.toString()
                    val email = response.data?.user?.email.toString()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    //longToast(name+" and "+email)
                }
                Status.ERROR -> {
                    val error = response.message
                    longToast("error = $error")
                }
                Status.LOADING ->{
                    longToast("loading data")
                }
            }
        })
        }
    }




}