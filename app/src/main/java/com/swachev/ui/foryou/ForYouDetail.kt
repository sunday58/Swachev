package com.swachev.ui.foryou

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.swachev.R
import com.swachev.adapters.StoreAdapter
import com.swachev.adapters.StoreDetailAdapter
import com.swachev.dataSource.remote.RetrofitBuilder
import com.swachev.model.Content
import com.swachev.model.ForYouData
import com.swachev.model.Product
import com.swachev.model.StoreItems
import com.swachev.utility.Event
import com.swachev.utility.Result
import com.swachev.utility.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ForYouDetail : Fragment() {

    var stores = ArrayList<Product>()

    private lateinit var forYouData: ForYouData
    private lateinit var saleCard: RelativeLayout
    private lateinit var companyName: TextView
    private lateinit var address: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var saleRecyclerView: RecyclerView
    private lateinit var adapter: StoreDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_for_you_detail, container, false)

        companyName = root.findViewById(R.id.foryou_companyName)
        address = root.findViewById(R.id.foryou_address)
        recyclerView = root.findViewById(R.id.foryou_RecyclerViewSeasonal)
        saleRecyclerView = root.findViewById(R.id.foryou_recyclerViewSale)

        saleCard = root.findViewById(R.id.saleCard)
        saleCard.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.navigation_foryou_itemDetail)
        }


        storeDetailData()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        getProduceData()

    }

//    fun getProduceData(){
//
//        RetrofitBuilder.storeApi.getStores().enqueue(object : Callback<StoreItems> {
//            override fun onResponse(call: Call<StoreItems>, response: Response<StoreItems>) {
//
//                Log.d("Detail Item",
//                    response.body()!!.content[forYouData.position].products[forYouData.position].toString()
//                )
//
//                stores.addAll(response.body()!!.content[forYouData.position].products )
//                 adapter = StoreDetailAdapter(requireContext(), stores)
//                recyclerView.adapter = adapter
//                recyclerView.adapter?.notifyDataSetChanged()
//
//            }
//
//            override fun onFailure(call: Call<StoreItems>, t: Throwable) {
//                Log.d("dataError", t.localizedMessage!!)
//                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

   @SuppressLint("SetTextI18n")
   private fun storeDetailData(){
        if (arguments != null) kotlin.run{
            forYouData = requireArguments().getSerializable("storeData") as ForYouData

            companyName.text = forYouData.storeItems.name
            address.text = forYouData.storeItems.address.city + " " + forYouData.storeItems.address.state + " " + forYouData.storeItems.address.country

            Log.d("Detail Item",
                forYouData.storeItems.products.toString()
            )
            stores.clear()
            stores.addAll(forYouData.storeItems.products)
            adapter = StoreDetailAdapter(requireContext(), stores)
            recyclerView.adapter = adapter
            saleRecyclerView.adapter = adapter
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

}
