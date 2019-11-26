package com.epitech.cashmanager.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epitech.cashmanager.EventBus.CountCartEvent
import com.epitech.cashmanager.db.CartDataSource
import com.epitech.cashmanager.db.CartDatabase
import com.epitech.cashmanager.db.CartIt
import com.epitech.cashmanager.db.LocalCartDataSource
import com.epitech.cashmanager.model.Product
import com.epitech.cashmanager.model.ProductList
import com.epitech.cashmanagerapp.R
import com.squareup.picasso.Picasso
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_cart_item.view.*
import kotlinx.android.synthetic.main.layout_product_item.view.*
import org.greenrobot.eventbus.EventBus

class  ProductAdapter(val context: Context, var products: ProductList) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val compositeDisposable: CompositeDisposable
    private val cartDataSource: CartDataSource

    init {
        compositeDisposable = CompositeDisposable()
        cartDataSource = LocalCartDataSource(CartDatabase.getInstance(context).cartDAO())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_product_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.content.size

    fun addItems(t: ProductList) {
        products = t
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bindProduct(products.content[position])
        holder.itemView.addItem.setOnClickListener {
            val cartItem = CartIt()
            cartItem.uid = "0"
            cartItem.product_id = products.content[position].id
            cartItem.product_name = products.content[position].name
            cartItem.product_imgUrl = products.content[position].imgUrl
            cartItem.product_price = products.content[position].price.toDouble()
            cartItem.product_quantity = 1

            cartDataSource.getItemWithAllOptionsInCart("0", cartItem.product_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<CartIt> {
                    override fun onSuccess(cartItemFromDB: CartIt) {
                        if (cartItemFromDB.equals(cartItem))
                        {
                            cartItemFromDB.product_quantity = cartItemFromDB.product_quantity + cartItem.product_quantity

                            cartDataSource.updateCart(cartItemFromDB)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : SingleObserver<Int> {
                                    override fun onSuccess(t: Int) {
                                        Toast.makeText(context, "Uptade Cart Success", Toast.LENGTH_SHORT).show()
                                        EventBus.getDefault().postSticky(CountCartEvent(true))
                                    }

                                    override fun onSubscribe(d: Disposable) {

                                    }

                                    override fun onError(e: Throwable) {
                                        Toast.makeText(context, "[UPDATE CART]" + e.message, Toast.LENGTH_SHORT).show()
                                    }

                                })
                        }
                        else
                        {
                            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    Toast.makeText(context, "Add to cart success", Toast.LENGTH_SHORT).show()
                                    EventBus.getDefault().postSticky(CountCartEvent(true))
                                }, {
                                        t: Throwable? ->  Toast.makeText(context, "[INSERT CART]" + t!!.message, Toast.LENGTH_SHORT).show()
                                }))
                        }
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        if (e.message!!.contains("empty"))
                        {
                            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    Toast.makeText(context, "Add to cart success", Toast.LENGTH_SHORT).show()
                                    EventBus.getDefault().postSticky(CountCartEvent(true))
                                }, {
                                        t: Throwable? ->  Toast.makeText(context, "[INSERT CART]" + t!!.message, Toast.LENGTH_SHORT).show()
                                }))
                        }
                        else
                            Toast.makeText(context, "[CART ERROR]" + e.message, Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

    fun onStop() {
        if (compositeDisposable != null)
            compositeDisposable.clear()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("CheckResult", "SetTextI18n")
        fun bindProduct(product: Product) {

            itemView.txt_product_name.text = product.name
            itemView.txt_product_price.text = "$${product.price.toString()}"

            Picasso.get().load(product.imgUrl).fit().into(itemView.img_product_image)

            /*  Observable.create(ObservableOnSubscribe<MutableList<CartIt>> {

                /*  itemView.addItem.setOnClickListener { view ->

                      val item = CartIt(product)



                      ShoppingCart.addItem(item)
                      //notify users
                      Snackbar.make(
                          (itemView.context as MainActivity).container,
                          "${product.name} added to your cart",
                          Snackbar.LENGTH_LONG
                      ).show()

                      it.onNext(ShoppingCart.getCart())

                  }*/

                  itemView.removeItem.setOnClickListener { view ->

                      val item = CartIt(product)

                      ShoppingCart.removeItem(item, itemView.context)

                      Snackbar.make(
                          (itemView.context as MainActivity).container,
                          "${product.name} removed from your cart",
                          Snackbar.LENGTH_LONG
                      ).show()

                      it.onNext(ShoppingCart.getCart())
                  }


              }).subscribe { cart ->
                  var quantity = 0

                  cart.forEach { cartItem ->
                      quantity += cartItem.quantity
                  }

                  (itemView.context as MainActivity).cart_size.text = quantity.toString()
                  Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
              }*/
        }



    }

}