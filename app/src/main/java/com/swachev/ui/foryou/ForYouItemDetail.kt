package com.swachev.ui.foryou

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.swachev.R

class ForYouItemDetail : Fragment() {

    companion object {
        fun newInstance() = ForYouItemDetail()
    }

    private lateinit var viewModel: ForYouItemDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.for_you_item_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForYouItemDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}