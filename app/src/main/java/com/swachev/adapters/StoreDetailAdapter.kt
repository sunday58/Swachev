package com.swachev.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swachev.MainActivity
import com.swachev.R
import com.swachev.model.Content
import com.swachev.model.ForYouData
import com.swachev.model.Product
import com.swachev.model.StoreItems
import org.w3c.dom.Text
import java.io.Serializable

class StoreDetailAdapter(context: Context,
                         private val list: List<Product?>?) :
    RecyclerView.Adapter< StoreDetailAdapter.StoreViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.foryou_detail_list_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {

        val item = list!![position]
        holder.storeDetailPrice.text = item!!.currency + item.rate + "/" + item.unit
        holder.storeDetailType.text = item.name

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("storeData", item)
            Navigation.findNavController(holder.itemView).navigate(R.id.navigation_foryou_itemDetail)
        }

    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val storeDetailPrice: TextView = itemView.findViewById(R.id.foryou_producePrice)
        val storeDetailType: TextView = itemView.findViewById(R.id.foryou_produceType)

    }

    override fun getItemCount(): Int {
        return list!!.count()
    }

}