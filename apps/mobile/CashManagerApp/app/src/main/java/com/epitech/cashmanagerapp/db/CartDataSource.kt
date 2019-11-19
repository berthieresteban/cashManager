package com.epitech.cashmanager.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CartDataSource {
    fun getAllCart(uid: String): Flowable<List<CartIt>>

    fun countItemInCart(uid: String): Single<Int>

    fun sumPrice(uid: String): Single<Double>

    fun getItemInCart(product_id: String, uid: String): Single<CartIt>

    fun insertOrReplaceAll(vararg CartIts: CartIt): Completable

    fun updateCart(cart: CartIt): Single<Int>

    //  fun deleteCart(cart: CartIt): Single<Int>

    // fun cleanCart(uid: String): Single<CartIt>

    fun getItemWithAllOptionsInCart(uid: String, product_id: String): Single<CartIt>
}