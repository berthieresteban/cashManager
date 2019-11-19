package com.epitech.cashmanager.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart", primaryKeys = [ "uid", "product_id" ])
class CartIt(
    @NonNull
    @ColumnInfo(name = "product_id")
    var product_id: String = "",

    @ColumnInfo(name ="product_name")
    var product_name: String? = "",

    @ColumnInfo(name = "product_description")
    var product_description: String? = "",

    @ColumnInfo(name ="product_price")
    var product_price: Double = 0.0,

    @ColumnInfo(name ="product_imgUrl")
    var product_imgUrl: String = "",

    @NonNull
    @ColumnInfo(name = "uid")
    var uid: String = "",


    @ColumnInfo(name = "product_quantity")
    var product_quantity: Int = 0




) {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is CartIt)
            return false
        val cartItem = other as CartIt?
        return cartItem!!.product_id == this.product_id
    }
}