package com.epitech.cashmanager.ui.cart

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epitech.cashmanager.db.CartDataSource
import com.epitech.cashmanager.db.CartDatabase
import com.epitech.cashmanager.db.CartIt
import com.epitech.cashmanager.db.LocalCartDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CartViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable
    private var cartDataSource: CartDataSource?=null
    private var mutableLiveDataCartItem: MutableLiveData<List<CartIt>>?=null

    init {
        compositeDisposable = CompositeDisposable()
    }

    fun initCartdataSource(context: Context) {
        cartDataSource = LocalCartDataSource(CartDatabase.getInstance(context).cartDAO())
    }

    fun getMutableLiveDataCartItem(): MutableLiveData<List<CartIt>> {
        if (mutableLiveDataCartItem == null)
            mutableLiveDataCartItem = MutableLiveData()
        getCartIt()
        return mutableLiveDataCartItem!!
    }

    private fun getCartIt() {
        compositeDisposable.addAll(cartDataSource!!.getAllCart("0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ cartIt ->
                mutableLiveDataCartItem!!.value = cartIt
            }, { t: Throwable? -> mutableLiveDataCartItem!!.value = null  }))
    }

    fun onStop() {
        compositeDisposable.clear()
    }

}