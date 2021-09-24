package com.example.effectiveexpensesmanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.effectiveexpensesmanager.models.roomdb.DataModel
import com.example.effectiveexpensesmanager.models.remote.network.api.Resource
import com.example.effectiveexpensesmanager.models.remote.requests.LoginRequestModel
import com.example.effectiveexpensesmanager.models.remote.requests.SignUpRequestModel
import com.example.effectiveexpensesmanager.models.remote.response.LoginResponse
import com.example.effectiveexpensesmanager.models.remote.response.SignUpResponseModel
import com.example.effectiveexpensesmanager.repository.DataRepo
import kotlinx.coroutines.Dispatchers

class DataViewModel(val repo: DataRepo): ViewModel() {


    fun userLogin(loginRequestModel: LoginRequestModel) : LiveData<Resource<LoginResponse>>{

        // running coroutine as  well as creating live data
        // ---> combo of coroutine and livedata
        return liveData(Dispatchers.IO) {
            val result = repo.login(loginRequestModel)
            emit(result) // sending or emitting data to all observer
            // mutablelivedata.value= result
        }
    }


    fun userSignUp(signUpRequestModel: SignUpRequestModel):LiveData<Resource<SignUpResponseModel>>{
        return liveData(Dispatchers.IO){
            val result = repo.Signup(signUpRequestModel)
            emit(result)
        }
    }


    fun addData(dataModel: DataModel){
        repo.addDataToRoom(dataModel)
    }

    fun getAllIncomeData(): LiveData<List<DataModel>>{
        return repo.getAllIncomeData()
    }
    fun getAllExpenseData(): LiveData<List<DataModel>>{
        return repo.getAllExpenseData()
    }

    fun updateData(dataModel: DataModel){
        repo.updateData(dataModel)
    }

    fun deleteData(dataModel: DataModel){
        repo.deleteData(dataModel)
    }
}