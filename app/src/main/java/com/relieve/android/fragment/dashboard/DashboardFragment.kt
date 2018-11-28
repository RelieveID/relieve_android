package com.relieve.android.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.navigation.fragment.findNavController
import com.relieve.android.helper.BottomNavBar
import com.relieve.android.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {
    companion object {
        const val INDEX_KEY = "index_key"
    }

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

        val current get() = if (stack.isNotEmpty()) stack.last() else -1
    }

    val fragments = listOf (
        DashboardHomeFragment(), DashboardDiscoverFragment(), DashboardChatFragment(), DashboardProfileFragment()
    )

    private val navStack = NavStack(4)

    private val bottomNavBar get() = BottomNavBar(navHome)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindNavClick()

        val index = if (navStack.current > -1) navStack.current else 0
        selectScreen(index)
    }

    private fun selectFragment(index: Int, isBackAction: Boolean = false) {
        if (index < fragments.size) {
            if (!isBackAction) navStack.add(index)
            childFragmentManager.transaction {
                replace(R.id.fragmentPort, fragments[index])
            }
        }
    }

    private fun selectToolbar(index: Int) {
        if (index < fragments.size) {
            when (fragments[index]) {
                is DashboardHomeFragment -> {
                    toolbar.title = getString(R.string.app_name)
                    toolbar.setTitleTextAppearance(context, R.style.AppTheme_MainToolbar)
                }
                is DashboardDiscoverFragment -> {
                    toolbar.title = getString(R.string.discover)
                    toolbar.setTitleTextAppearance(context, R.style.AppTheme_OtherToolbar)
                }
                is DashboardChatFragment -> {
                    toolbar.title = getString(R.string.chat)
                    toolbar.setTitleTextAppearance(context, R.style.AppTheme_OtherToolbar)
                }
                is DashboardProfileFragment -> {
                    toolbar.title = getString(R.string.profile)
                    toolbar.setTitleTextAppearance(context, R.style.AppTheme_OtherToolbar)
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
                findNavController().navigate(R.id.action_dashboardFragment_to_callFragment)
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
