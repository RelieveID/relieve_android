package com.relieve.android.activity

import android.content.Intent
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

    private fun selectToolbar(index: Int) {
        if (index < fragments.size) {
            when (fragments[index]) {
                is HomeFragment -> {
                    toolbar.title = getString(R.string.app_name)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_MainToolbar)
                }
                is DiscoverFragment -> {
                    toolbar.title = getString(R.string.discover)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_OtherToolbar)
                }
                is ChatFragment -> {
                    toolbar.title = getString(R.string.chat)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_OtherToolbar)
                }
                is ProfileFragment -> {
                    toolbar.title = getString(R.string.profile)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_OtherToolbar)
                }
            }
        }
    }

    private fun bindNavClick() {
        BottomNavBar(navHome).apply {
            setHomeClickListener {
                selectFragment(0)
                selectToolbar(0)
            }
            setDiscoverClickListener {
                selectFragment(1)
                selectToolbar(1)
            }
            setCallClickListener {
                startActivity(Intent(this@MainActivity, CallActivity::class.java))
            }
            setChatClickListener {
                selectFragment(2)
                selectToolbar(2)
            }
            setProfileClickListener {
                selectFragment(3)
                selectToolbar(3)
            }
        }
    }
}
