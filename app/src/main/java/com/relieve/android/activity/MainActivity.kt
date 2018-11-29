package com.relieve.android.activity

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.relieve.android.helper.BottomNavBar
import android.os.Bundle
import androidx.navigation.findNavController
import com.relieve.android.R

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        findNavController(R.id.mainNavHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
//        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
//        navController.addOnNavigatedListener { _, destination ->
//            when (destination.id) {
//                R.id.boardingLoginFragment, R.id.boardingRegisterFragment -> {
//                    toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back_arrow)
//                }
//                R.id.walkthroughFragment -> {
//                    toolbar.navigationIcon = null
//                }
//            }
//
//        }
    }
}
