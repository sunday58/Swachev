package com.swachev.ui.foryou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.swachev.R
import com.swachev.model.ForYouData

/**
 * A simple [Fragment] subclass.
 */
class ForYouDetail : Fragment() {

    private lateinit var forYouData: ForYouData
    private lateinit var saleCard: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_for_you_detail, container, false)

        saleCard = root.findViewById(R.id.saleCard)
        saleCard.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.navigation_foryou_itemDetail)
        }



        return root
    }

   private fun storeDetailData(){
        if (arguments != null) kotlin.run{
            forYouData = requireArguments().getSerializable("storeData") as ForYouData
        }
    }

}
