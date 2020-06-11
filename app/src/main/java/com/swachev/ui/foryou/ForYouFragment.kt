package com.swachev.ui.foryou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.swachev.R

class ForYouFragment : Fragment() {

    private lateinit var categoryViewModel: ForYouViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        categoryViewModel =
                ViewModelProviders.of(this).get(ForYouViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_foryou, container, false)



        return root
    }
}
