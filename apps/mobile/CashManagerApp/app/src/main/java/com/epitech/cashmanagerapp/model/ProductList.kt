package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("content")
    var content: List<Product> = arrayListOf()
)