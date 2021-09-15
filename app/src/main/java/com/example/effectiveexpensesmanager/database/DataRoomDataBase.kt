package com.example.effectiveexpensesmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataModel::class],version = 1)
abstract class DataRoomDataBase: RoomDatabase() {

    abstract fun getDataDAO() : DataDAO

    companion object{

        private var INSTANCE : DataRoomDataBase? = null

        fun getDataBaseObject(context: Context) : DataRoomDataBase{
            if (INSTANCE== null){
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    DataRoomDataBase::class.java,
                    "data_db"
                )

                INSTANCE = builder.build()
                return INSTANCE as DataRoomDataBase
            }
            else return INSTANCE as DataRoomDataBase
        }
    }
}