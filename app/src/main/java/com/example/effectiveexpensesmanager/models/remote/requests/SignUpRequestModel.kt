package com.example.effectiveexpensesmanager.models.remote.requests


import com.google.gson.annotations.SerializedName

data class SignUpRequestModel(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: String?
)