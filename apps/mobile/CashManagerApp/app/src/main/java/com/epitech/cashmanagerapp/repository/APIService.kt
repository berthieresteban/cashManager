package com.epitech.cashmanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.epitech.cashmanager.helper.Utils
import com.epitech.cashmanager.model.ProductList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    companion object {
        var apiInterface: APIInterface

        private val AUTH = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6InVzZXIxIiwiZXhwIjoxNTc2MjAwNTIzLCJyb2wiOlsiUk9MRV9VU0VSIl19.4GeLUssNRAfEMCKy3eL-pBwr3g-876s8nZtJScdOhWstMpPgXz4eKkspaq4-1tlB6wlu_jx9a5h4o-N4ZSrK4w"


        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", AUTH)
                    .method(original.method(), original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
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