package com.epitech.cashmanagerapp

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    var description: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("imgUrl")
    var imgUrl: String? = null
)