package com.swachev

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         navView = findViewById(R.id.nav_view)

         navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_favorite, R.id.navigation_foryou, R.id.navigation_yourList,
        R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setClickListener()
    }

    //listener for bottom navigation
   private fun setClickListener(){
        navController.addOnDestinationChangedListener { _, item: NavDestination, _ ->
            if (item.id == R.id.navigation_category || item.id == R.id.navigation_signIn
                ||item.id == R.id.navigation_register ||item.id == R.id.navigation_registerNext) {
                hideBottomNav()
                hideToolBar()
            }else {
                showBottomNav()
                showToolBar()
            }
        }

    }

   private fun hideBottomNav() {
        navView.isVisible = false
    }

   private fun showBottomNav() {
        navView.isVisible = true
    }
   private fun hideToolBar(){
        supportActionBar!!.hide()
    }

   private fun showToolBar(){
        supportActionBar!!.show()
    }
}
