package com.example.effectiveexpensesmanager.views.adapters.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.effectiveexpensesmanager.R
import com.example.effectiveexpensesmanager.models.remote.network.api.Status
import com.example.effectiveexpensesmanager.models.remote.requests.SignUpRequestModel
import com.example.effectiveexpensesmanager.models.roomdb.DataDAO
import com.example.effectiveexpensesmanager.models.roomdb.DataRoomDataBase
import com.example.effectiveexpensesmanager.repository.DataRepo
import com.example.effectiveexpensesmanager.viewmodels.DataViewModel
import com.example.effectiveexpensesmanager.viewmodels.DataViewModelFactory
import com.example.effectiveexpensesmanager.views.adapters.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    lateinit var roomDb: DataRoomDataBase
    lateinit var dataDAO: DataDAO
    lateinit var dataViewModel: DataViewModel

    lateinit var navController: NavController

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
        navController = Navigation.findNavController(view)

        btnSignUp.setOnClickListener {

            val signUpRequestModel = SignUpRequestModel(
                age = 25,
                email = "amol12345@yahoo.com",
                name = "amol",
                password = "1234567rkp"
            )
            dataViewModel.userSignUp(signUpRequestModel).observe(viewLifecycleOwner, Observer {
                val response = it

                when (response.status) {
                    Status.SUCCESS -> {
                        val name = response.data?.user?.name.toString()
                        val email = response.data?.user?.email.toString()

                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            activity,
                            "name is $name and email is $email /n Signup Successfully",
                            Toast.LENGTH_LONG
                        )
                    }
                    Status.ERROR -> {
                        val error = response.message
                        Toast.makeText(activity, "error is $error ", Toast.LENGTH_LONG)
                    }
                    Status.LOADING -> {
                        Toast.makeText(activity, "Data loading", Toast.LENGTH_LONG)
                    }
                }
            })
        }

        tvLoginPress.setOnClickListener {
            navController.navigate(
                R.id.action_signUpFragment_to_loginFragment
            )
        }

    }
}
