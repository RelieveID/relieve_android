package com.relieve.android.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.relieve.android.helper.BottomNavBar
import com.relieve.android.R
import com.relieve.android.fragment.main.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    class NavStack(val size: Int) {
        private val stack = mutableListOf<Int>()

        fun add(index : Int) {
            stack.add(index)
            if (stack.size > size) stack.removeAt(0)
        }

        fun pop() : Int {
            if (stack.size > 0) stack.removeAt(stack.size - 1)

            return if (stack.size > 0) return stack.last()
            else -1
        }
    }

    val fragments = listOf (
        MainHomeFragment(), MainDiscoverFragment(), MainChatFragment(), MainProfileFragment()
    )

    private val bottomNavBar by lazy {
        BottomNavBar(navHome)
    }

    private val navStack = NavStack(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindNavClick()
        selectScreen(0)
    }

    override fun onBackPressed() {
        val lastIndex = navStack.pop()
        if (lastIndex > 0) {
            selectScreen( lastIndex, true)
        } else {
            super.onBackPressed()
        }
    }

    private fun selectFragment(index: Int, isBackAction: Boolean = false) {
        if (index < fragments.size) {
            if (!isBackAction) navStack.add(index)
            supportFragmentManager.transaction {
                replace(R.id.fragmentPort, fragments[index])
            }
        }
    }

    private fun selectToolbar(index: Int) {
        if (index < fragments.size) {
            when (fragments[index]) {
                is MainHomeFragment -> {
                    toolbar.title = getString(R.string.app_name)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_MainToolbar)
                }
                is MainDiscoverFragment -> {
                    toolbar.title = getString(R.string.discover)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_OtherToolbar)
                }
                is MainChatFragment -> {
                    toolbar.title = getString(R.string.chat)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_OtherToolbar)
                }
                is MainProfileFragment -> {
                    toolbar.title = getString(R.string.profile)
                    toolbar.setTitleTextAppearance(this, R.style.AppTheme_OtherToolbar)
                }
            }
        }
    }

    private fun selectScreen(index: Int, isBackAction: Boolean = false) {
        selectFragment(index, isBackAction)
        selectToolbar(index)
        bottomNavBar.selectButton(index)
    }

    private fun bindNavClick() {
        bottomNavBar.apply {
            setHomeClickListener {
                selectScreen(0)
            }
            setDiscoverClickListener {
                selectScreen(1)
            }
            setCallClickListener {
                startActivity(Intent(this@MainActivity, CallActivity::class.java))
            }
            setChatClickListener {
                selectScreen(2)
            }
            setProfileClickListener {
                selectScreen(3)
            }
        }
    }
}
