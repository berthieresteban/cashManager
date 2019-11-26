package com.epitech.cashmanager.db

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class LocalCartDataSource(private val cartDAO: CartDAO): CartDataSource {
    override fun getItemWithAllOptionsInCart(uid: String, product_id: String): Single<CartIt> {
        return cartDAO.getItemWithAllOptionsInCart(uid, product_id)
    }

    override fun getAllCart(uid: String): Flowable<List<CartIt>> {
        return cartDAO.getAllCart(uid)
    }

    override fun countItemInCart(uid: String): Single<Int> {
        return cartDAO.countItemInCart(uid)
    }

    override fun sumPrice(uid: String): Single<Double> {
        return cartDAO.sumPrice(uid)
    }

    override fun getItemInCart(product_id: String, uid: String): Single<CartIt> {
        return cartDAO.getItemInCart(product_id, uid)
    }

    override fun insertOrReplaceAll(vararg CartIts: CartIt): Completable {
        return cartDAO.InsertOrReplaceAll(*CartIts)
    }

    override fun updateCart(cart: CartIt): Single<Int> {
        return cartDAO.updateCart(cart)
    }

    override fun deleteCart(cart: CartIt): Single<Int> {
          return cartDAO.deleteCart(cart)
    }

    /*  override fun cleanCart(uid: String): Single<CartIt> {
          return cartDAO.cleanCart(uid)
      }*/

}