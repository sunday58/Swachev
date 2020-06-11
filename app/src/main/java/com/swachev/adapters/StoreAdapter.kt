package com.swachev.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swachev.R
import com.swachev.model.StoreItems

class StoreAdapter(private val listener: OnStoreClickListener) :
    ListAdapter<StoreItems, StoreAdapter.StoreViewHolder>(StoreDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.for_you_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    interface OnStoreClickListener{
        fun OnItemClick(item: StoreItems)
    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val storeName: TextView = itemView.findViewById(R.id.storeName)
        val storeLocation: TextView = itemView.findViewById(R.id.storeLocation)

        fun bind(item: StoreItems, listener: OnStoreClickListener) = with(itemView) {

            storeName.text = item.content[adapterPosition].name
            storeLocation.text = item.content[adapterPosition].category

            itemView.setOnClickListener {
                listener.OnItemClick(item)
            }
        }
    }

    class StoreDiffUtil : DiffUtil.ItemCallback<StoreItems>() {

        override fun areItemsTheSame(oldItem: StoreItems, newItem: StoreItems): Boolean {
            return oldItem.content == newItem.content
        }

        override fun areContentsTheSame(oldItem: StoreItems, newItem: StoreItems): Boolean {
            return oldItem.equals(newItem)
        }
    }

}