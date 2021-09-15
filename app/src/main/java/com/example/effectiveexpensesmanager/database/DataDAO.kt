package com.example.effectiveexpensesmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(dataModel: DataModel)

    @Query("select * from records where category = 'Income' ")
    fun getAllIncomeData() : LiveData<List<DataModel>>

    @Query("select * from records where category = 'Expense' ")
    fun getAllExpenseData() : LiveData<List<DataModel>>

    @Update
    fun updateData(dataModel: DataModel)

    @Delete
    fun deleteData(dataModel: DataModel)

}