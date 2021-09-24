package com.example.effectiveexpensesmanager.repository

import androidx.lifecycle.LiveData
import com.example.effectiveexpensesmanager.models.roomdb.DataDAO
import com.example.effectiveexpensesmanager.models.roomdb.DataModel
import com.example.effectiveexpensesmanager.models.remote.network.api.Network
import com.example.effectiveexpensesmanager.models.remote.network.api.Resource
import com.example.effectiveexpensesmanager.models.remote.network.api.ResponseHandler
import com.example.effectiveexpensesmanager.models.remote.network.api.TasksAPI
import com.example.effectiveexpensesmanager.models.remote.requests.LoginRequestModel
import com.example.effectiveexpensesmanager.models.remote.requests.SignUpRequestModel
import com.example.effectiveexpensesmanager.models.remote.response.LoginResponse
import com.example.effectiveexpensesmanager.models.remote.response.SignUpResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

class DataRepo(private val dataDAO: DataDAO) {


    private val api: TasksAPI = Network.getRetrofit().create(
        TasksAPI::class.java)
    private val responseHandler = ResponseHandler()
    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MGE0YmI3OTAzMjdlN2MwNmE2MTk1ODYiLCJpYXQiOjE2MzIxMzg2ODR9.cTxpYQrTfvramIOSPih6b1hJO_x1G-V2GmaRnTYSjU0"

    suspend fun login(loginRequestModel: LoginRequestModel): Resource<LoginResponse> {

        return try {
            val response = api.login(loginRequestModel)
            return responseHandler.handleSuccess(response)

        }catch (e: Exception){
            return responseHandler.handleException(e)
        }
    }

    suspend fun Signup(signUpRequestModel: SignUpRequestModel):Resource<SignUpResponseModel>{

        return try {
            val response = api.signUp(token,signUpRequestModel)
            return responseHandler.handleSuccess(response)
        }catch (e: Exception){
            return responseHandler.handleException(e)
        }
    }

    fun addDataToRoom(dataModel: DataModel){
        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.addData(dataModel)
        }
    }

    fun getAllIncomeData(): LiveData<List<DataModel>> {
        return dataDAO.getAllIncomeData()
    }
    fun getAllExpenseData(): LiveData<List<DataModel>> {
        return dataDAO.getAllExpenseData()
    }

    fun updateData(dataModel: DataModel){
        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.updateData(dataModel)
        }
    }

    fun deleteData(dataModel: DataModel){
        CoroutineScope(Dispatchers.IO).launch {
            dataDAO.deleteData(dataModel)
        }
    }
}