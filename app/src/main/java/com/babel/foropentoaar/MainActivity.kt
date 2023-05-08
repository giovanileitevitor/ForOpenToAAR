package com.babel.foropentoaar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.babel.foropentoaar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.timwe.tti2sdk.ui.splash.SplashActivity
import com.timwe.init.ScreenCallback
import com.timwe.init.Tti2
import com.timwe.init.Tti2Request
import com.timwe.init.UTM
import com.timwe.init.UserProfile


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Call SDK", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            try {
                val listPlans = listOf<String>("prepaid", "postpaid")
                val listTier = listOf<String>("gold", "silver", "platinum", "diamond")
                val listLang = listOf<String>("en", "idn")

                val userProfile =  UserProfile()

                userProfile.userMsisdn = "62458000010"
                userProfile.email = "email@email.com"
                userProfile.lang = listLang[0]
                userProfile.tier = listTier[0]
                userProfile.plan = listPlans[0]

                val intent = Intent(this@MainActivity, SplashActivity::class.java)
                intent.putExtra("USER_PROFILE_KEY", userProfile)
                intent.putExtra("IS_DEBUGGABLE", true)
                startActivity(intent)

            }catch (r: java.lang.Exception){
                r.printStackTrace()
            }

        }
    }

}