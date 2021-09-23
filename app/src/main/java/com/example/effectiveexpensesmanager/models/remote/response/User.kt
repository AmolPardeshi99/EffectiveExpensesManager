package com.example.effectiveexpensesmanager.models.remote.response
import com.google.gson.annotations.SerializedName

data class User (

	@SerializedName("age") val age : Int,
	@SerializedName("_id") val _id : String,
	@SerializedName("name") val name : String,
	@SerializedName("email") val email : String,
	@SerializedName("createdAt") val createdAt : String,
	@SerializedName("updatedAt") val updatedAt : String,
	@SerializedName("__v") val __v : Int
)