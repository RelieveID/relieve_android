package com.relieve.android.screen.fragment.boarding

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.relieve.android.R
import com.relieve.android.helper.*
import com.relieve.android.rsux.base.EditTextChangeListener
import com.relieve.android.rsux.component.SnackBarItem
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.helper.isEmailValid
import com.relieve.android.screen.viewmodel.BoardingViewModel
import kotlinx.android.synthetic.main.fragment_boarding_login.*
import kotlinx.android.synthetic.main.sheet_forgot_pass.*

class BoardingLoginFragment : RsuxFragment<BoardingViewModel.BoardingState, BoardingViewModel>() {
    override val vModel by lazy {  ViewModelProviders.of(this).get(BoardingViewModel::class.java) }

    init {
        layoutId = R.layout.fragment_boarding_login
    }

    override fun render(state: BoardingViewModel.BoardingState) {
        toolbarBoardingLogin.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_boardingLoginFragment_to_boardingRegisterFragment)
        }

        tvForgotPassword.setOnClickListener {
            forgotPassClick()
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
                vModel.loginClick(username.toString(), password.toString()) { isSuccess, resToken ->
                    if (isSuccess) {
                        preferencesHelper?.apply {
                            isSignedIn = true
                            token = resToken?.token
                            tokenRefresh = resToken?.refreshToken
                            tokenExpire = resToken?.expiresIn ?: 0
                        }

                        findNavController().navigate(R.id.action_boardingLoginFragment_to_dashboardFragment)
                    } else {
                        SnackBarItem.make(rootBoardingLogin, Snackbar.LENGTH_LONG).apply {
                            setMessage(getString(R.string.wrong_account))
                            setButtonText(getString(R.string.ok))
                            setButtonClick { dismiss() }
                            show()
                        }
                    }
                }
            }
        }
    }

    private fun emailForgotSubmit(input: TextInputLayout, dialog: BottomSheetDialog) {
        if (input.editText?.text.toString().isEmailValid()) {
            vModel.forgotPassClick(input.editText?.text.toString()) { isSuccess, _ ->
                dialog.dismiss()

                if (!isSuccess) {
                    SnackBarItem.make(rootBoardingLogin, Snackbar.LENGTH_INDEFINITE).apply {
                        setMessage(getString(R.string.unknown_error))
                        setButtonText(getString(R.string.ok))
                        setButtonClick { dismiss() }
                        show()
                    }
                }
            }
        } else {
            input.error = getString(R.string.error_email_format)
        }
    }

    private fun forgotPassClick() {
        val contextNotNull = context ?: return

        BottomSheetDialog(contextNotNull).apply {
            setContentView(layoutInflater.inflate(R.layout.sheet_forgot_pass, null))

            val input = inputForgotUsername
            input.editText?.addTextChangedListener(object : EditTextChangeListener() {
                override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                    if (text.isEmpty()) input.error = getString(R.string.please_fill)
                    else {
                        input.error = null
                        input.isErrorEnabled = false
                    }
                }
            })

            this.tvSubmitForgotPass.setOnClickListener {
                if (!input.editText?.text.isNullOrEmpty()) {
                    emailForgotSubmit(input, this)
                } else {
                    input.error = getString(R.string.please_fill)
                }
            }
        }.show()
    }
}
