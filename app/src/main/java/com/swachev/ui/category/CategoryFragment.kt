package com.swachev.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView

import com.swachev.R

/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment() {

    private lateinit var shopper: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val root = inflater.inflate(R.layout.fragment_category, container, false)

        shopper = root.findViewById(R.id.shopper)
        shopper.setOnClickListener { 
            Navigation.findNavController(root).navigate(R.id.action_navigation_category_to_navigation_signIn)
        }


        return root
    }

}
