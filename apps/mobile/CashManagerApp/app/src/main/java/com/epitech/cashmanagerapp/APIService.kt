package com.epitech.cashmanagerapp

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("Products")
    fun getProducts(
    ): Call<Result>

}
