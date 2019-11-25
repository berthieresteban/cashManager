package com.epitech.cashmanager.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.epitech.cashmanager.EventBus.UpdateItemInCart
import com.epitech.cashmanager.db.CartDataSource
import com.epitech.cashmanager.db.CartDatabase
import com.epitech.cashmanager.db.CartIt
import com.epitech.cashmanager.db.LocalCartDataSource
import com.epitech.cashmanager.model.CartItem
import com.epitech.cashmanagerapp.R
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_cart_item.view.*
import kotlinx.android.synthetic.main.layout_product_item.view.*
import org.greenrobot.eventbus.EventBus
import org.w3c.dom.Text
import java.lang.StringBuilder

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartIt>) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    internal val compositeDisposable: CompositeDisposable
    internal val cartDataSource: CartDataSource

    init {
        compositeDisposable = CompositeDisposable()
        cartDataSource = LocalCartDataSource(CartDatabase.getInstance(context).cartDAO())
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ShoppingCartAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_cart_item, parent, false))
    }

    // This returns the size of the list.
    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ShoppingCartAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(cartItems[position].product_imgUrl)
            .into(viewHolder.img_cart)
        viewHolder.txt_food_name.text = StringBuilder(cartItems[position].product_name!!)
        viewHolder.txt_food_price.text = StringBuilder("").append(cartItems[position].product_price)
        viewHolder.number_button.number = cartItems[position].product_quantity.toString()

        viewHolder.number_button.setOnValueChangeListener { view, oldValue, newValue ->
            cartItems[position].product_quantity = newValue
            EventBus.getDefault().postSticky(UpdateItemInCart(cartItems[position]))
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var img_cart: ImageView
        lateinit var txt_food_name: TextView
        lateinit var txt_food_price: TextView
        lateinit var number_button: ElegantNumberButton

        init {
            img_cart = itemView.findViewById(R.id.img_cart) as ImageView
            txt_food_name = itemView.findViewById((R.id.txt_food_name)) as TextView
            txt_food_price = itemView.findViewById(R.id.txt_food_price) as TextView
            number_button = itemView.findViewById(R.id.number_button) as ElegantNumberButton
        }

        @SuppressLint("CheckResult", "SetTextI18n")
        fun bindItem(cartItem: CartItem) {

            // This displays the cart item information for each item
            Picasso.get().load(cartItem.product.imgUrl).fit().into(itemView.img_product_image)

            itemView.txt_product_name.text = cartItem.product.name

            itemView.txt_product_price.text = "$${cartItem.product.price}"

            //  itemView.number_button.number = cartItem.quantity.toString()

        }
    }

}
