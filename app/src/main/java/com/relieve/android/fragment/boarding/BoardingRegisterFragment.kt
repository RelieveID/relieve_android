package com.relieve.android.fragment.boarding


import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.relieve.android.R
import kotlinx.android.synthetic.main.fragment_boarding_register.*

class BoardingRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_boarding_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enableTextClick()
        render()
    }

    private fun enableTextClick() {
        tvTermsNCondition.linksClickable = true
        tvTermsNCondition.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun render() {
        toolbarBoardingRegister.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_boardingRegisterFragment_to_dashboardFragment)
        }
    }
}
