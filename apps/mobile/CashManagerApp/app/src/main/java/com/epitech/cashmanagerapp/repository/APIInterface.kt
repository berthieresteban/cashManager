package com.epitech.cashmanager.repository

import com.epitech.cashmanager.model.ProductList
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("Products")
    fun getProducts(): Call<ProductList>
}