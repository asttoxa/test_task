package ru.antdroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_currency.view.*
import ru.antdroid.data.Stock

class CurrencyRecyclerAdapter(private val currencyList: List<Stock>) :
    RecyclerView.Adapter<CurrencyRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRecyclerAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_currency,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "name: ${currencyList[position].name}"
        holder.volume.text = "volume: ${currencyList[position].volume}"
        holder.amount.text = "amount: ${currencyList[position].price.amount}"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name!!
        val volume = view.volume!!
        val amount = view.amount!!
    }
}