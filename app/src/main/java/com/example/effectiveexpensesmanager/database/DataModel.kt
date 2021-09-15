package com.example.effectiveexpensesmanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
class DataModel(
    @ColumnInfo(name = "category") var category: String ,
    @ColumnInfo(name = "amount") var amount : Int,
    @ColumnInfo(name = "desc") var desc :String,
    @ColumnInfo(name = "date") var date :String
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null
}