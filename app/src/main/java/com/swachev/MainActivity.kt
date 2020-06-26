package com.swachev

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.default_toolBar)
        setSupportActionBar(toolbar)

         navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_favorite, R.id.navigation_foryou, R.id.navigation_yourList,
        R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setDestinationListener()
    }

    //listener for bottom navigation
    private fun setDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest = resources.getResourceName(destination.id)

            when (destination.id) {
                R.id.navigation_foryou, R.id.navigation_yourList, R.id.navigation_favorite -> {
                    hideCustomToolBar()
                    showBottomNav()
                }
                R.id.navigation_category, R.id.navigation_foryou_Detail, R.id.navigation_foryou_itemDetail -> {
                    hideCustomToolBar()
                    hideBottomNav()
                }
                R.id.navigation_register ->{
                    hideCustomToolBar()
                    hideBottomNav()
                }
                R.id.navigation_registerFinish ->{
                    hideCustomToolBar()
                    hideBottomNav()
                }
                R.id.navigation_registerNext ->{
                    hideCustomToolBar()
                    hideBottomNav()
                }
                R.id.navigation_signIn ->{
                    hideCustomToolBar()
                    hideBottomNav()
                }
                else ->{
                    showCustomToolBar()
                    showBottomNav()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination!!.id == R.id.navigation_foryou)
            showDialog()
        else if (navController.currentDestination!!.id == R.id.navigation_favorite ||
            navController.currentDestination!!.id == R.id.navigation_yourList ||
                navController.currentDestination!!.id == R.id.navigation_profile)
            navController.navigate(R.id.navigation_foryou)
        else
            super.onBackPressed()
    }

    private fun showDialog() {
        val dialog = MaterialAlertDialogBuilder(this@MainActivity)
        dialog.setTitle("Exiting?")
        dialog.setIcon(R.drawable.ic_close_black_24dp)
        dialog.setMessage("Are you sure you want to exit?")
            .setPositiveButton(
                "YES"
            ) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                exitProcess(0)
            }
            .setNegativeButton(
                "NO"
            ) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
        dialog.create().show()
    }


    private fun hideCustomToolBar() {
        toolbar.isVisible = false
    }

    private fun showCustomToolBar() {
        toolbar.isVisible = true
    }

   private fun hideBottomNav() {
        navView.isVisible = false
    }

   private fun showBottomNav() {
        navView.isVisible = true
    }

}
