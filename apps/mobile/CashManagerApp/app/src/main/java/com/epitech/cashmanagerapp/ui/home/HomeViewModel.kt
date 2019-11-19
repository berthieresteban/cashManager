package com.epitech.cashmanager.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epitech.cashmanager.model.ProductList
import com.epitech.cashmanager.repository.APIService

class HomeViewModel : ViewModel() {

    var list: LiveData<ProductList>

    init {
        list =  APIService.getProductList()
    }

    fun fetchAllData() : LiveData<ProductList> = list
}