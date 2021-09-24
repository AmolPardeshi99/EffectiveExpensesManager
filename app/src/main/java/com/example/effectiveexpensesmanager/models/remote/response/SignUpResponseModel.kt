package com.example.effectiveexpensesmanager.models.remote.response


import com.google.gson.annotations.SerializedName

data class SignUpResponseModel(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: UserX?
)