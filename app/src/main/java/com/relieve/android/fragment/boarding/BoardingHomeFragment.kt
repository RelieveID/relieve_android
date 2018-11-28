package com.relieve.android.fragment.boarding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.relieve.android.R
import kotlinx.android.synthetic.main.fragment_boarding_home.*

class BoardingHomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boarding_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        render()
    }

    private fun render() {
        tvSignNow.setOnClickListener {
            findNavController().navigate(R.id.action_boardingHomeFragment_to_boardingLoginFragment)
        }

        tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_boardingHomeFragment_to_boardingRegisterFragment)
        }
    }

    private fun setupNavigation() {
//        toolbar.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
//
//        context?.run {
//            findNavController().addOnNavigatedListener { _, destination ->
//                when (destination.id) {
//                    R.id.boardingLoginFragment, R.id.boardingRegisterFragment -> {
//                        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back_arrow)
//                    }
//                    R.id.walkthroughFragment -> {
//                        toolbar.navigationIcon = null
//                    }
//                }
//
//            }
//        }
    }
}
