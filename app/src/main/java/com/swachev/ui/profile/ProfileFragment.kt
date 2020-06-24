package com.swachev.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.swachev.R


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var layout: RelativeLayout
    private lateinit var subImageView: ImageView
    private lateinit var layoutName: TextView
    private var appBarExpanded = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.profile_fragment, container, false)
        appBarLayout = root.findViewById(R.id.appbar)

        val toolbar: androidx.appcompat.widget.Toolbar = root.findViewById(R.id.anim_toolbar);
        layout = root.findViewById(R.id.sub_imageLayout)
        subImageView = root.findViewById(R.id.user_subImage)
        layoutName = root.findViewById(R.id.layout_name)

        collapsingToolbar = root.findViewById(R.id.collapsing_toolbar);

        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset -> //  Vertical offset == 0 indicates appBar is fully expanded.
            if (Math.abs(verticalOffset) > 200){
                appBarExpanded = false
                layout.isVisible = false
                subImageView.isVisible = true
                layoutName.text = "Profile"
            }else{
                appBarExpanded = true
                layout.isVisible = true
                subImageView.isVisible = false
                layoutName.text = "Emeka Johnson"
            }

        })

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
