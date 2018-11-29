package com.relieve.android.fragment.boarding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.relieve.android.R
import com.relieve.android.rsux.base.EditTextChangeListener
import com.relieve.android.viewmodel.boarding.BoardingLoginVM
import kotlinx.android.synthetic.main.fragment_boarding_login.*

class BoardingLoginFragment : Fragment() {
    private val vModel by lazy {
        ViewModelProviders.of(this).get(BoardingLoginVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_boarding_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        render()
    }

    private fun render() {
        toolbarBoardingLogin.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_boardingLoginFragment_to_boardingRegisterFragment)
        }

        inputUsername.editText?.addTextChangedListener(object : EditTextChangeListener() {
            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                if (text.isEmpty()) inputUsername.error = getString(R.string.please_fill)
                else {
                    inputUsername.error = null
                    inputUsername.isErrorEnabled = false
                }
            }
        })

        inputPassword.editText?.addTextChangedListener(object : EditTextChangeListener() {
            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                if (text.isEmpty()) inputPassword.error = getString(R.string.please_fill)
                else {
                    inputPassword.error = null
                    inputPassword.isErrorEnabled = false
                }
            }
        })

        tvLogin.setOnClickListener {
            val username = inputUsername.editText?.text
            val password = inputPassword.editText?.text

            if (username.isNullOrEmpty()) {
                inputUsername.error = getString(R.string.please_fill)
            }

            if (password.isNullOrBlank()) {
                inputPassword.error = getString(R.string.please_fill)
            }

            if (!username.isNullOrEmpty() && !password.isNullOrBlank()) {
                vModel.loginClick(username.toString(), password.toString()) { isSuccess ->
                    if (isSuccess) {
                        findNavController().navigate(R.id.action_boardingLoginFragment_to_dashboardFragment)
                    }
                }
            }
        }
    }
}
