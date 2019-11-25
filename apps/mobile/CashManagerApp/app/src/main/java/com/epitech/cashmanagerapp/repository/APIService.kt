package com.epitech.cashmanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.epitech.cashmanager.helper.Utils
import com.epitech.cashmanager.model.ProductList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    companion object {
        var apiInterface: APIInterface

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiInterface = retrofit.create(APIInterface::class.java)
        }

        fun getProductList() : LiveData<ProductList> {
            val data = MutableLiveData<ProductList>()

            apiInterface.getProducts().enqueue(object : Callback<ProductList> {
                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    data.setValue(response.body())
                }

                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    data.setValue(null)
                    t.printStackTrace()
                }
            })

            return data
        }
    }
}