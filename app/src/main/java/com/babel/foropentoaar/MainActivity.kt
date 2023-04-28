package com.babel.foropentoaar

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.babel.foropentoaar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.timwe.init.ScreenCallback
import com.timwe.init.Tti2
import com.timwe.init.UTM
import com.timwe.init.UserProfile


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Call SDK", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            val listPlans = listOf<String>("prepaid", "postpaid")
            val listTier = listOf<String>("gold", "silver", "platinum", "diamond")
            val listLang = listOf<String>("en", "idn")

            val tti2 = Tti2()
            val userProfile =  UserProfile()
            userProfile.email = "email@email.com"
            userProfile.userMsisdn = ""
            userProfile.lang = listLang[0]
            userProfile.tier = listTier[0]
            userProfile.plan = listPlans[0]

            val screenCallback = ScreenCallback { redirectKey ->
                Log.d("SDK", "ScreenCallback: $redirectKey")
            }
            val redirectKey = ""
            tti2.ui(this@MainActivity, userProfile, redirectKey,  screenCallback)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}