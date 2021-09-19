package com.example.effectiveexpensesmanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.effectiveexpensesmanager.repository.DataRepo

class DataViewModelFactory (val repo: DataRepo): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(repo) as T
    }

}