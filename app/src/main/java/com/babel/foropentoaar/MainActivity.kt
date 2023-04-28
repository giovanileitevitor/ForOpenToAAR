package com.babel.foropentoaar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.babel.foropentoaar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.timwe.init.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Call SDK", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            val listPlans = listOf<String>("prepaid", "postpaid")
            val listTier = listOf<String>("gold", "silver", "platinum", "diamond")
            val listLang = listOf<String>("en", "idn")

            val userProfile =  UserProfile()

            userProfile.userMsisdn = "62458000010"
            userProfile.email = "email@email.com"
            userProfile.lang = listLang[0]
            userProfile.tier = listTier[0]
            userProfile.plan = listPlans[0]

            val tti2: Tti2 = Tti2.newInstance("a52f8547-650a-49ea-b01d-3f4aaf49d485", true)
            val tti2Request = Tti2Request()
            tti2Request.userProfile = userProfile

            tti2.getUserProfile(tti2Request){
                Log.d("SDK", "OnResponse")
            }
            val screenCallback = ScreenCallback { redirectKey ->
                Log.d("SDK", "ScreenCallback: $redirectKey")
            }
            val redirectKey = ""
            tti2.ui(
                this@MainActivity,
                userProfile,
                UTM(
                    "utmSourceTesteAndroid",
                    "utmCampaignTesteAndroid",
                    "utmMediumTesteAndroid",
                    "utmTermTesteAndroid",
                    "utmContenTesteAndroid"
                ),
                redirectKey,
                screenCallback
            )
//            tti2.ui(this@MainActivity, userProfile, redirectKey,  screenCallback)


        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}