package com.relieve.android.screen.fragment.walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.relieve.android.R
import kotlinx.android.synthetic.main.fragment_walkthrough.*
import kotlinx.android.synthetic.main.fragment_walkthrough_item.view.*

class WalkthroughFragment : Fragment() {
    companion object {
        const val IMAGE_KEY = "IMAGE_KEY"
        const val TITLE_KEY = "TITLE_KEY"
        const val SUBTITLE_KEY = "SUBTITLE_KEY"
    }
    class WalkthroughItemFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var image = ""
            var title = ""
            var subtitle = ""
            arguments?.also {
                image = it.getString(IMAGE_KEY, "")
                title = it.getString(TITLE_KEY, "")
                subtitle = it.getString(SUBTITLE_KEY, "")
            }
            return inflater.inflate(R.layout.fragment_walkthrough_item, container, false).apply {
                if (title.isNotEmpty()) tvWalkThroughTitle.text = title
                if (subtitle.isNotEmpty()) tvWalkThroughDetail.text = subtitle
            }
        }
    }

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_walkthrough, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render()
    }

    val fragments = listOf (
        WalkthroughItemFragment().apply {
            arguments = Bundle()
            arguments?.apply {
                putString(IMAGE_KEY, "")
                putString(TITLE_KEY, "Tambahkan keluarga dan orang terpenting mu ke daftar kerabat")
                putString(SUBTITLE_KEY, "Jangan sampai kamu ketinggalan informasi mengenai kesehatan mereka")
            }
        },
        WalkthroughItemFragment().apply {
            arguments = Bundle()
            arguments?.apply {
                putString(IMAGE_KEY, "")
                putString(TITLE_KEY, "Selalu ingat memberi kabar, dengan sekali tap")
                putString(SUBTITLE_KEY, "Notifikasi harian akan membuat mu selalu ingat memberitahu kondisi mu kepada kerabat")
            }
        },
        WalkthroughItemFragment().apply {
            arguments = Bundle()
            arguments?.apply {
                putString(IMAGE_KEY, "")
                putString(TITLE_KEY, "Selalu waspada bencana dapat terjadi kapan saja")
                putString(SUBTITLE_KEY, "Relieve akan memperingatkan mu jika ada bencana terjadi di dekat mu, tetap tenang dan siap evakuasi")
            }
        },
        WalkthroughItemFragment().apply {
            arguments = Bundle()
            arguments?.apply {
                putString(IMAGE_KEY, "")
                putString(TITLE_KEY, "Ingin tau kondisi terupdate suatu lokasi bencana?")
                putString(SUBTITLE_KEY, "Pantau terus perkembangan penanggulangan bencana, jangan lagi lewatkan informasi penting")
            }
        }
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
