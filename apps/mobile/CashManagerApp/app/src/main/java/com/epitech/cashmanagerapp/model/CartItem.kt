package com.epitech.cashmanager.model

data class CartItem(
    var product: Product,
    var quantity: Int = 0,
    var uid: String = ""
)