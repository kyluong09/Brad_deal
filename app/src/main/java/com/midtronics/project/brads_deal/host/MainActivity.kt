package com.midtronics.project.brads_deal.host

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.midtronics.project.brads_deal.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.canonicalName

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tool_bar)
        // Check if user has internet. Otherwise ask user to turn on WIFI before using our app
        if(!isNetworkAvailable()){
            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.attention))
                .setMessage(getString(R.string.attention_message))
                .setNeutralButton(getString(R.string.okay),{dialog, which ->
                    // Close app
                    System.exit(0)
                })
                .show()
        }
        initilizeNavController()

    }


    private fun initilizeNavController(){
        Log.d(TAG, "Initializing NavigationController")
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfig = AppBarConfiguration(setOf(
            R.id.firstFragment
        ),drawer_layout)

        tool_bar.setupWithNavController(navController,appBarConfig)
        navigation_view.setupWithNavController(navController)
    }

    private  fun isNetworkAvailable(): Boolean{
        val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }
}
