package com.epitech.cashmanager.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface CartDAO {
    @Query("SELECT * FROM Cart WHERE uid=:uid")
    fun getAllCart(uid: String): Flowable<List<CartIt>>

    @Query("SELECT SUM(product_quantity) FROM Cart WHERE uid=:uid")
    fun countItemInCart(uid: String): Single<Int>

    @Query("SELECT SUM(product_price * product_quantity) FROM Cart WHERE uid=:uid")
    fun sumPrice(uid: String): Single<Double>

    @Query("SELECT * FROM Cart WHERE product_id=:product_id AND uid=:uid")
    fun getItemInCart(product_id: String, uid: String): Single<CartIt>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertOrReplaceAll(vararg cart: CartIt):Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCart(cart:CartIt):Single<Int>

    /*  @Delete()
      fun deleteCart(cart:CartIt):Single<Int>

      @Query("DELETE FROM Cart WHERE uid=:uid")
      fun cleanCart(uid: String): Single<CartIt>*/

    @Query("SELECT * FROM Cart WHERE product_id=:product_id AND uid=:uid")
    fun getItemWithAllOptionsInCart(uid: String, product_id: String): Single<CartIt>
}
