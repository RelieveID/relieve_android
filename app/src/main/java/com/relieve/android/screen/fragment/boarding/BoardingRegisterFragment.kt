package com.relieve.android.screen.fragment.boarding


import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.relieve.android.R
import com.relieve.android.rsux.base.EditTextChangeListener
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.helper.isEmailValid
import com.relieve.android.screen.viewmodel.BoardingViewModel
import kotlinx.android.synthetic.main.fragment_boarding_register.*

class BoardingRegisterFragment : RsuxFragment<BoardingViewModel.BoardingState, BoardingViewModel>() {
    override val vModel by lazy {  ViewModelProviders.of(this).get(BoardingViewModel::class.java) }

    private val textInputs by lazy {
        listOf (
            inputUsername,
            inputPassword,
            inputConfirmPassword,
            inputEmail
        )
    }

    init {
        layoutId = R.layout.fragment_boarding_register
    }

    override fun render(state: BoardingViewModel.BoardingState) {
        textInputs.forEach {
            it.editText?.addTextChangedListener(object : EditTextChangeListener() {
                override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                    if (text.isEmpty()) it.error = getString(R.string.please_fill)
                    else {
                        it.error = null
                        it.isErrorEnabled = false
                    }
                }
            })
        }

        toolbarBoardingRegister.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        tvRegisterNext.setOnClickListener {
            val username = inputUsername.editText?.text
            val password = inputPassword.editText?.text
            val confirmPassword = inputConfirmPassword.editText?.text
            val email = inputEmail.editText?.text

            val texts = listOf(username, password, confirmPassword, email)

            var allIsValid = true
            texts.forEachIndexed { index, editable ->
                if (editable.isNullOrEmpty()) {
                    textInputs[index].error = getString(R.string.please_fill)
                    allIsValid = false
                }
            }

            if (!email.toString().isEmailValid()) {
                allIsValid = false
                inputEmail.error = getString(R.string.error_email_format)
            }

            if (password.toString().length < 8) {
                allIsValid = false
                inputPassword.error = getString(R.string.error_password_length)
            }

            if (password.toString() != confirmPassword.toString()) {
                allIsValid = false
                inputPassword.error = getString(R.string.error_password_match)
                inputConfirmPassword.error = getString(R.string.error_password_match)
            }

            if (allIsValid) {
                BoardingRegisterFragmentDirections
                    .actionBoardingRegisterFragmentToBoardingRegisterFragment2(
                        email.toString(),
                        username.toString(),
                        password.toString()
                    )
                    .run {
                        findNavController().navigate(this)
                    }
            }
        }
    }
}
