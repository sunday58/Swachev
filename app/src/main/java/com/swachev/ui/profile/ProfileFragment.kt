package com.swachev.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat.checkSelfPermission

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.swachev.R
import java.util.jar.Manifest


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
    private lateinit var selectPic: ImageView
    private lateinit var header: ImageView
    private lateinit var selectHeader: ImageView
    private var appBarExpanded = true
    private val PERMISSION_CODE_READ = 101
    private val PERMISSION_CODE_WRITE = 102
    private val IMAGE_PICK_CODE = 1

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
        selectPic = root.findViewById(R.id.select_pic)
        header = root.findViewById(R.id.header)
        selectHeader = root.findViewById(R.id.select_header)

        collapsingToolbar = root.findViewById(R.id.collapsing_toolbar);

        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset -> //  Vertical offset == 0 indicates appBar is fully expanded.
            if (Math.abs(verticalOffset) > 200){
                appBarExpanded = false
                layout.isVisible = false
                subImageView.isVisible = true
                layoutName.text = "Profile"
                selectHeader.isVisible = false
            }else{
                appBarExpanded = true
                layout.isVisible = true
                subImageView.isVisible = false
                layoutName.text = "Emeka Johnson"
                selectHeader.isVisible = true
            }

        })

        //select pictures
        selectHeader.setOnClickListener {
            checkPermissionForImage()
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun selectPicture(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            header.setImageURI(data?.data)
        }
    }

    private fun checkPermissionForImage(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if ((checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED))
            {
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse  = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(permission,PERMISSION_CODE_READ)
                requestPermissions(permissionCoarse,PERMISSION_CODE_WRITE)
            } else {
                selectPicture()
            }

        }
    }

}
