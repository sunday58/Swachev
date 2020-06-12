package com.swachev.ui.foryou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swachev.R
import com.swachev.adapters.StoreAdapter
import com.swachev.utility.State

class ForYouFragment : Fragment() {

    private lateinit var forYouViewModel: ForYouViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        forYouViewModel = ViewModelProvider(requireActivity()).get(ForYouViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_foryou, container, false)

        recyclerView = root.findViewById(R.id.for_you_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(parentFragment?.context,
            LinearLayoutManager.VERTICAL, false)

        responseMess()
        storeResult()
        return root
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    //get response message
   private fun responseMess(){
        forYouViewModel.responseMessage.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when(result.status) {
                    State.LOADING -> showToast("Loading")
                    State.SUCCESS -> showToast(result.message!!)
                    State.ERROR -> showToast(result.message!!)
                }
            }
        })
    }

    //get store results
   private fun storeResult(){
        forYouViewModel.getStoresFromLocal().observe(viewLifecycleOwner, Observer {storeItems ->

            val adapter = StoreAdapter(requireContext(), storeItems)
            recyclerView.adapter = adapter
            recyclerView.adapter?.notifyDataSetChanged()

        })
    }

}
