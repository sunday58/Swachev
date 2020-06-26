package com.swachev.ui.yourList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.swachev.R
import com.swachev.adapters.YourListAdapter
import com.swachev.utility.State

class YourListFragment : Fragment() {

    private lateinit var yourListViewModel: YourListViewModel
    private lateinit var recyclerViewSeasonal: RecyclerView
    private lateinit var recyclerViewSale: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        yourListViewModel =
                ViewModelProviders.of(this).get(YourListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_your_list, container, false)


        recyclerViewSeasonal = root.findViewById(R.id.yourList_RecyclerViewSeasonal)
        recyclerViewSale = root.findViewById(R.id.yourList_recyclerViewSale)


        responseMess()
        storeResults()
        return root
    }

    //get response message
    private fun responseMess(){
        yourListViewModel.responseMessage.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when(result.status) {
                    State.LOADING -> showToast("Loading")
                    State.SUCCESS -> showToast(result.message!!)
                    State.ERROR -> showToast(result.message!!)
                }
            }
        })
    }

    private fun storeResults(){
        yourListViewModel.getStoresFromLocal().observe(viewLifecycleOwner, Observer {storeItems ->
            val adapter = YourListAdapter(requireContext(), storeItems)
            recyclerViewSale.adapter = adapter
            recyclerViewSeasonal.adapter = adapter
            recyclerViewSeasonal.adapter?.notifyDataSetChanged()
            recyclerViewSale.adapter?.notifyDataSetChanged()

        })
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
