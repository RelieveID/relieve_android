package com.relieve.android.fragment.walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.relieve.android.R
import com.relieve.android.helper.PreferencesHelper
import kotlinx.android.synthetic.main.fragment_walkthrough.*

class WalkthroughFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_walkthrough, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render()
    }

    val fragments = listOf(
        WalkthroughItemFragment(),
        WalkthroughItemFragment(),
        WalkthroughItemFragment(),
        WalkthroughItemFragment()
    )

    private fun render() {
        vpWalkThrough.adapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int) = fragments[position]
            override fun getCount() = fragments.size
        }
        vpWalkThrough.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                if (position == fragments.size - 1) {
                    tvNexSlide.text = getString(R.string.done)
                } else {
                    tvNexSlide.text = getString(R.string.next)
                }
            }
        })

        dotsIndicator.setViewPager(vpWalkThrough)

        tvNexSlide.setOnClickListener {
            val current = vpWalkThrough.currentItem
            if (current < fragments.size - 1) {
                vpWalkThrough.setCurrentItem(current + 1, true)
            } else {
                findNavController().navigateUp()
            }
        }
    }
}
