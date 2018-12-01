package com.relieve.android.screen.fragment.dashboard

import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.relieve.android.helper.BottomNavBar
import com.relieve.android.R
import com.relieve.android.helper.hasSeenWalkthrough
import com.relieve.android.helper.token
import com.relieve.android.helper.tokenFCM
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.helper.PreferencesHelper
import com.relieve.android.screen.viewmodel.DashboardViewHolder
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : RsuxFragment<DashboardViewHolder.DashboardState, DashboardViewHolder>() {
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

    override val vModel by lazy {
        ViewModelProviders.of(this).get(DashboardViewHolder::class.java).also {
            it.createRelieveService(preferencesHelper?.token)
        }
    }

    val fragments = listOf (
        DashboardHomeFragment(), DashboardDiscoverFragment(), DashboardChatFragment(), DashboardProfileFragment()
    )

    private val navStack = NavStack(4)

    private val bottomNavBar get() = BottomNavBar(navHome)

    init {
        layoutId = R.layout.fragment_dashboard
    }

    override fun requestData() {
        vModel.updateFcmToken(preferencesHelper?.tokenFCM ?: "")
    }

    override fun render(state: DashboardViewHolder.DashboardState) {
        bindNavClick()

        shouldShowWalkthrought()

        val index = if (navStack.current > -1) navStack.current else 0
        selectScreen(index)
    }

    private fun shouldShowWalkthrought() {
        context?.run {
            if (!PreferencesHelper(this).hasSeenWalkthrough) {
                PreferencesHelper(this).hasSeenWalkthrough = true
                findNavController().navigate(R.id.action_dashboardFragment_to_walkthroughFragment)
            }
        }
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
