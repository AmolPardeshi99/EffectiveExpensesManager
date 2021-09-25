package com.example.effectiveexpensesmanager.views.adapters.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.models.remote.network.api.Status
import com.example.effectiveexpensesmanager.models.remote.requests.LoginRequestModel
import com.example.effectiveexpensesmanager.models.roomdb.DataDAO
import com.example.effectiveexpensesmanager.models.roomdb.DataRoomDataBase
import com.example.effectiveexpensesmanager.repository.DataRepo
import com.example.effectiveexpensesmanager.viewmodels.DataViewModel
import com.example.effectiveexpensesmanager.viewmodels.DataViewModelFactory
import com.example.effectiveexpensesmanager.views.adapters.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    lateinit var roomDb: DataRoomDataBase
    lateinit var dataDAO: DataDAO
    lateinit var dataViewModel: DataViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        roomDb = DataRoomDataBase.getDataBaseObject(context)
        dataDAO = roomDb.getDataDAO()

        val repo = DataRepo(dataDAO)
        val factory = DataViewModelFactory(repo)
        dataViewModel = ViewModelProviders.of(this, factory).get(DataViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {

            var username: String = etUsername1.text.toString()
            var password: String = etPassword1.text.toString()

            val loginRequestModel = LoginRequestModel(
                userName = username,
                password = password
            )

            if (username != null && password != null) {
                dataViewModel.userLogin(loginRequestModel).observe(viewLifecycleOwner, Observer {
                    val response = it

                    when (response.status) {

                        Status.SUCCESS -> {
                            val name = response.data?.user?.name

                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                        }
                        Status.ERROR -> {
                            val error = response.message
                            Toast.makeText(activity, "Erroe is $error", Toast.LENGTH_SHORT).show()
                        }
                        Status.LOADING -> {

                        }
                    }
                })
            }
        }
    }

}


//            val loginRequestModel = LoginRequestModel(
//                userName = "pradeep1706108@gmail.com",
//                password = "dhankhar"
//            )

