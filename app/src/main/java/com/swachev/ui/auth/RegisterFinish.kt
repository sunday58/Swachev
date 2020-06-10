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

class RegisterFinish : Fragment() {

    companion object {
        fun newInstance() = RegisterFinish()
    }

    private lateinit var viewModel: RegisterFinishViewModel
    private lateinit var registerFinish: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.register_finish_fragment, container, false)

        registerFinish = root.findViewById(R.id.registerFinish_button)
        registerFinish.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_registerFinish_to_navigation_foryou)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterFinishViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
