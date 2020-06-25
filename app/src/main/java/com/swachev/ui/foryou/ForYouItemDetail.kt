package com.swachev.ui.foryou

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.swachev.R
import com.swachev.adapters.StoreDetailAdapter
import com.swachev.adapters.StoreItemDetailAdapter
import com.swachev.dataSource.remote.RetrofitBuilder
import com.swachev.model.ForYouData
import com.swachev.model.ForYouItemData
import com.swachev.model.Product
import com.swachev.model.StoreItems
import com.swachev.utility.Event
import com.swachev.utility.Result
import com.swachev.utility.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForYouItemDetail : Fragment() {


    private lateinit var forYouItemData: Product
    private lateinit var itemName: TextView
    private lateinit var itemShortDesc: TextView
    private lateinit var itemPrice: TextView
    private lateinit var itemDetail: TextView
    private lateinit var itemImage: RoundedImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StoreItemDetailAdapter
    private lateinit var close: ImageView
    private lateinit var subtract : ImageView
    private lateinit var add: ImageView
    private lateinit var quantity: TextView

    var stores = ArrayList<Product>()
    var numItems = 1

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
        close = root.findViewById(R.id.foryou_itemClose)
        recyclerView = root.findViewById(R.id.foryou_itemRecyclerView)
        add = root.findViewById(R.id.add)
        subtract = root.findViewById(R.id.subtract)
        quantity = root.findViewById(R.id.quantity)

        produceCount()

        //go back
        close.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.navigation_foryou_Detail)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        forYouItemDetails()
        getProduceData()
    }

    private fun produceCount(){
            add.setOnClickListener {
               numItems += 1
                quantity.text = numItems.toString()
            }
            subtract.setOnClickListener {
                if (numItems == 1) {
                    Toast.makeText(requireContext(), "item cant be zero", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener

                }
                numItems -= 1
                quantity.text = numItems.toString()


            }

    }

       private fun getProduceData(){

        RetrofitBuilder.storeApi.getStores().enqueue(object : Callback<StoreItems> {
            override fun onResponse(call: Call<StoreItems>, response: Response<StoreItems>) {

                stores.clear()
                stores.addAll(response.body()!!.content[1].products )
                 adapter = StoreItemDetailAdapter(requireContext(), stores)
                recyclerView.adapter = adapter
                recyclerView.adapter?.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<StoreItems>, t: Throwable) {
                Log.d("dataError", t.localizedMessage!!)
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
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

        }
    }
}