package com.relieve.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.relieve.android.helper.BottomNavBar
import com.relieve.android.R
import com.relieve.android.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fragments = listOf (
        HomeFragment(), DiscoverFragment(), ChatFragment(), ProfileFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindNavClick()
        selectFragment(0)
    }

    private fun selectFragment(index: Int) {
        if (index < fragments.size) {
            supportFragmentManager.transaction {
                replace(R.id.fragmentPort, fragments[index])
            }
        }
    }

    private fun bindNavClick() {
        BottomNavBar(navHome).apply {
            setHomeClickListener {
                selectFragment(0)
            }
            setDiscoverClickListener {
                selectFragment(1)
            }
            setCallClickListener {
//                selectFragment(2)
            }
            setChatClickListener {
                selectFragment(2)
            }
            setProfileClickListener {
                selectFragment(3)
            }
        }
    }
}
