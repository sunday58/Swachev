package com.swachev.ui.auth

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton

import com.swachev.R

class RegisterNext : Fragment() {

    companion object {
        fun newInstance() = RegisterNext()
    }

    private lateinit var viewModel: RegisterNextViewModel
    private lateinit var registerNext: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.register_next_fragment, container, false)

        registerNext = root.findViewById(R.id.registerNext_button)
        registerNext.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_registerNext_to_navigation_registerFinish)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterNextViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
