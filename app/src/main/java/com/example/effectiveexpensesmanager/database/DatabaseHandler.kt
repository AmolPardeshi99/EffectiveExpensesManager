package com.example.effectiveexpensesmanager.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.effectiveexpensesmanager.adapter.DataModel

class DatabaseHandler(val context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "MoneyManager"
        val DB_VERSION = 1

        val TABLE_NAME1 = "Income"
        val ID = "id"
        val DESC = "desc"
        val AMT = "amt"
        val DATE = "Date"

        val TABLE_NAME2 = "Expense"
        val ID2 = "id"
        val DESC2 = "desc"
        val AMT2 = "amt"
        val DATE2 = "date"

        var totalIncome=0
        var totalExpense = 0

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // called once initially  can run quries

        // initializing table:- table1- income
        val CREATE_INCOME_QUERY = "CREATE TABLE" +
                " $TABLE_NAME1 (" +
                "$ID INTEGER PRIMARY KEY, " +
                "$AMT INT, " +
                "$DATE TEXT, " +
                "$DESC TEXT)"

        // executing query
        db?.execSQL(CREATE_INCOME_QUERY)

        val CREATE_EXPENSE_QUERY = "CREATE TABLE" +
                " $TABLE_NAME2 (" +
                "$ID2 INTEGER PRIMARY KEY, " +
                "$AMT2 INT, " +
                "$DATE2 TEXT, " +
                "$DESC2 TEXT)"

        // executing query
        db?.execSQL(CREATE_EXPENSE_QUERY)
    }

    // Inserting Income  data
    fun insertIncome(amount: Int, desc: String,date: String) {
        val db = writableDatabase

        // content values helps to create map
        val values = ContentValues()
        values.put(AMT, amount)
        values.put(DATE, date)
        values.put(DESC, desc)


        val idValue = db.insert(TABLE_NAME1, null, values)
        if (idValue.toInt() == -1) {
            Toast.makeText(context, "Failed to insert the data", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data inserted Successfully!", Toast.LENGTH_SHORT).show()
            totalIncome = totalIncome+amount
        }
    }
    // Inserting Expense  data
    fun insertExpense(amount: Int, desc: String,date: String) {
        val db = writableDatabase

        // content values helps to create map
        val values = ContentValues()
        values.put(AMT2, amount)
        values.put(DATE2, date)
        values.put(DESC2, desc)


        val idValue = db.insert(TABLE_NAME2, null, values)
        if (idValue.toInt() == -1) {
            Toast.makeText(context, "Failed to insert the data", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data inserted Successfully!", Toast.LENGTH_SHORT).show()
            totalExpense = totalExpense+amount
        }
    }

    // getting Income Data
    fun getIncomeData(): MutableList<DataModel> {
        var dataList = mutableListOf<DataModel>()
        val db = readableDatabase
        val query = "select * from $TABLE_NAME1"

        val cursor = db?.rawQuery(query, null)

        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val id = cursor.getInt(cursor.getColumnIndex(ID))
                val desc = cursor.getString(cursor.getColumnIndex(DESC))
                val amount = cursor.getInt(cursor.getColumnIndex(AMT))
                val date = cursor.getString(cursor.getColumnIndex(DATE))

                val dataModel = DataModel()
                dataModel.id = id
                dataModel.desc = desc
                dataModel.amount = amount
                dataModel.date = date
                dataModel.category = "Income"

                dataList.add(dataModel)
            } while (cursor.moveToNext())
        }
        return dataList
    }
    // get expense data
    fun getExpenseData(): MutableList<DataModel> {
        var dataList = mutableListOf<DataModel>()
        val db = readableDatabase
        val query = "select * from $TABLE_NAME2"

        val cursor = db?.rawQuery(query, null)

        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val id = cursor.getInt(cursor.getColumnIndex(ID2))
                val desc = cursor.getString(cursor.getColumnIndex(DESC2))
                val amount = cursor.getInt(cursor.getColumnIndex(AMT2))
                val date = cursor.getString(cursor.getColumnIndex(DATE2))

                val dataModel = DataModel()
                dataModel.id = id
                dataModel.desc = desc
                dataModel.amount = amount
                dataModel.date = date
                dataModel.category = "Expense"

                dataList.add(dataModel)
            } while (cursor.moveToNext())
        }
        return dataList
    }

    // edit Income data
    fun editIncome(dataModel: DataModel) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(AMT, dataModel.amount)
        contentValues.put(DESC, dataModel.desc)
        contentValues.put(DATE, dataModel.date)

        val result = db.update(TABLE_NAME1, contentValues, "id=${dataModel.id}", null)
        // showing toast on basis of
        if (result != -1) {
            Toast.makeText(context, "Data is updated", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data update failed", Toast.LENGTH_SHORT).show()
        }
    }

    // edit Expense data
    fun editExpense(dataModel: DataModel) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(AMT2, dataModel.amount)
        contentValues.put(DESC2, dataModel.desc)
        contentValues.put(DATE2, dataModel.date)

        val result = db.update(TABLE_NAME2, contentValues, "id=${dataModel.id}", null)
        // showing toast on basis of
        if (result != -1) {
            Toast.makeText(context, "Data is updated", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data update failed", Toast.LENGTH_SHORT).show()
        }
    }

    // deleting Income Data
    fun deleteIncome(dataModel: DataModel) {
        val db = writableDatabase
        val resut = db.delete(TABLE_NAME1, "id=${dataModel.id}", null)

        if (resut == -1) {
            Toast.makeText(context, "Data deletion failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data is deleted", Toast.LENGTH_SHORT).show()
        }

    }

    // deleting Expense Data
    fun deleteExpense(dataModel: DataModel) {
        val db = writableDatabase
        val resut = db.delete(TABLE_NAME2, "id=${dataModel.id}", null)

        if (resut == -1) {
            Toast.makeText(context, "Data deletion failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data is deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun getTotalIncome(): Int {
        return totalIncome
    }

    fun getTotalExpense(): Int{
        return totalExpense
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // every time we release app this method is invoked
    }

}