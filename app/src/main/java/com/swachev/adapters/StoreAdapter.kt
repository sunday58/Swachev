package com.swachev.adapters

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
import com.swachev.model.StoreItems
import java.io.Serializable

class StoreAdapter(context: Context,
        private val list: List<Content?>?) :
    RecyclerView.Adapter< StoreAdapter.StoreViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.for_you_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {

        val item = list!![position]
       holder.storeName.text = item!!.name
      holder.storeLocation.text = item.address.city

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("storeData", item)
//            val navController = Navigation.findNavController(holder.itemView)
//            navController.navigate(R.id.action_navigation_foryou_to_navigation_foryou_Detail, bundle)
        }

    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val storeName: TextView = itemView.findViewById(R.id.storeName)
        val storeLocation: TextView = itemView.findViewById(R.id.storeLocation)

    }

    override fun getItemCount(): Int {
        return list!!.count()
    }

}