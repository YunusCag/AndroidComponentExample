package com.yunuscagliyan.androidcomponentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        navController=Navigation.findNavController(this,R.id.nav_host_fragment)

        setUpSideMenu()
        setUpActionBar()
        drawer_layout.useCustomBehavior(Gravity.START)
        drawer_layout.setRadius(Gravity.START, 25F)

    }
    private fun setUpSideMenu(){
        nav_view.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }
    private fun setUpActionBar(){
        NavigationUI.setupActionBarWithNavController(this,navController,drawer_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var navigated=NavigationUI.onNavDestinationSelected(item,navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawer_layout)|| super.onSupportNavigateUp()
    }

}
