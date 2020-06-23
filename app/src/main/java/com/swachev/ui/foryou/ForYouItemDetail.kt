package com.swachev.ui.foryou

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.swachev.R
import com.swachev.adapters.StoreDetailAdapter
import com.swachev.model.ForYouData
import com.swachev.model.ForYouItemData
import com.swachev.model.Product

class ForYouItemDetail : Fragment() {


    private lateinit var forYouItemData: Product
    private lateinit var itemName: TextView
    private lateinit var itemShortDesc: TextView
    private lateinit var itemPrice: TextView
    private lateinit var itemDetail: TextView
    private lateinit var itemImage: RoundedImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StoreDetailAdapter

    companion object {
        fun newInstance() = ForYouItemDetail()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.for_you_item_detail_fragment, container, false)

        itemName = root.findViewById(R.id.foryou_itemName)
        itemShortDesc = root.findViewById(R.id.foryou_itemSubDetail)
        itemPrice = root.findViewById(R.id.foryou_itemPrice)
        itemDetail = root.findViewById(R.id.foryou_itemDetail)
        itemImage = root.findViewById(R.id.foryou_itemImage)
        recyclerView = root.findViewById(R.id.foryou_itemRecyclerView)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        forYouItemDetails()

    }

   @SuppressLint("SetTextI18n")
   private fun forYouItemDetails() {
        if (arguments != null) kotlin.run {
            forYouItemData = requireArguments().getSerializable("storeItemData") as Product

            itemName.text = forYouItemData.name
            itemShortDesc.text = forYouItemData.category
            itemPrice.text = forYouItemData.currency +
                    forYouItemData.rate + "/" +
                    forYouItemData.unit
            itemDetail.text = forYouItemData.description

            val item = ArrayList<Product>()
            item.clear()
            item.add(forYouItemData)
            adapter = StoreDetailAdapter(requireContext(), item)
            recyclerView.adapter = adapter
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}