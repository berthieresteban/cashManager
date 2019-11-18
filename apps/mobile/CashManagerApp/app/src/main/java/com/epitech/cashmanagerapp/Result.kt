package com.epitech.cashmanagerapp

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("content")
    var content: List<Product> = arrayListOf()
)