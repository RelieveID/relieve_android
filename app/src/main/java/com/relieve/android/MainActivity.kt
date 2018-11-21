package com.relieve.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.relieve.android.components.BottomNavBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindNavClick()
    }

    private fun bindNavClick() {
        BottomNavBar(navHome).apply {
            setHomeClickListener {

            }
            setDiscoverClickListener {

            }
            setCallClickListener {

            }
            setChatClickListener {

            }
            setProfileClickListener {

            }
        }
    }
}
