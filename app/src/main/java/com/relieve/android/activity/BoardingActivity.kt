package com.relieve.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.relieve.android.R
import kotlinx.android.synthetic.main.activity_boarding.*

class BoardingActivity : AppCompatActivity() {

    private val navController by lazy {
        findNavController(R.id.navBoardingFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)

        setupNavigation()
    }

    private fun setupNavigation() {
        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
        navController.addOnNavigatedListener { _, destination ->
            when (destination.id) {
                R.id.boardingLoginFragment, R.id.boardingRegisterFragment -> {
                    toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back_arrow)
                }
                R.id.walkthroughFragment -> {
                    toolbar.navigationIcon = null
                }
            }

        }
    }
}
