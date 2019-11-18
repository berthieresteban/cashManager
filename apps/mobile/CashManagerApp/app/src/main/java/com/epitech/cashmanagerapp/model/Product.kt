package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    var description: String? = null,

    @SerializedName("id")
    var id: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("price")
    var price: String = "",

    @SerializedName("imgUrl")
    var imgUrl: String = ""
)