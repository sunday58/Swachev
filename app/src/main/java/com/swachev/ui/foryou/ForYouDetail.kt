package com.swachev.ui.foryou

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.swachev.R
import com.swachev.model.Content
import com.swachev.model.ForYouData

/**
 * A simple [Fragment] subclass.
 */
class ForYouDetail : Fragment() {

    //viemodel
    private lateinit var viewModel: ForYouDetailViewModel

    private lateinit var forYouData: Content
    private lateinit var saleCard: RelativeLayout
    private lateinit var companyName: TextView
    private lateinit var address: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_for_you_detail, container, false)

        companyName = root.findViewById(R.id.foryou_companyName)
        address = root.findViewById(R.id.foryou_address)

        saleCard = root.findViewById(R.id.saleCard)
        saleCard.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.navigation_foryou_itemDetail)
        }


        storeDetailData()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForYouDetailViewModel::class.java)

    }

   @SuppressLint("SetTextI18n")
   private fun storeDetailData(){
        if (arguments != null) kotlin.run{
            forYouData = requireArguments().getSerializable("storeData") as Content

            companyName.text = forYouData.name
            address.text = forYouData.address.city + " " + forYouData.address.state + " " + forYouData.address.country
        }
    }

}
