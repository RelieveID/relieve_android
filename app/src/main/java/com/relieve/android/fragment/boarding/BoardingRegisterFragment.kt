package com.relieve.android.fragment.boarding


import android.app.DatePickerDialog
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.relieve.android.R
import com.relieve.android.network.data.relieve.Register
import com.relieve.android.rsux.base.EditTextChangeListener
import com.relieve.android.rsux.helper.isEmailValid
import com.relieve.android.viewmodel.boarding.BoardingRegisterVM
import kotlinx.android.synthetic.main.fragment_boarding_register.*
import java.util.*

class BoardingRegisterFragment : Fragment() {
    private val vModel by lazy {
        ViewModelProviders.of(this).get(BoardingRegisterVM::class.java)
    }

    private val textInputs by lazy {
        listOf (
            inputUsername,
            inputPassword,
            inputConfirmPassword,
            inputFullName,
            inputDateOfBirth,
            inputEmail,
            inputPhoneNumberArea,
            inputPhoneNumber
        )
    }

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
        tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_boardingRegisterFragment_to_dashboardFragment)
        }

        inputDateOfBirth.editText?.setOnClickListener {
            val c = Calendar.getInstance()
            val currentYear = c.get(Calendar.YEAR)
            val currentMonth = c.get(Calendar.MONTH)
            val currentDay = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(inputDateOfBirth.context, { _, year, month, day ->
                inputDateOfBirth.editText?.setText(getString(R.string.bod_format, year, month+1, day))
            }, currentYear, currentMonth, currentDay).show()
        }


        tvRegister.setOnClickListener {
            val username = inputUsername.editText?.text
            val password = inputPassword.editText?.text
            val confirmPassword = inputConfirmPassword.editText?.text
            val fullName = inputFullName.editText?.text
            val dob = inputDateOfBirth.editText?.text
            val email = inputEmail.editText?.text
            val phoneArea = inputPhoneNumberArea.editText?.text
            val phoneNumber = inputPhoneNumber.editText?.text

            val texts = listOf(username, password, confirmPassword, fullName, dob, email, phoneArea, phoneNumber)

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
                vModel.registerClick(Register(
                    username.toString(),
                    password.toString(),
                    fullName.toString(),
                    email.toString(),
                    "${ phoneArea.toString() } ${ phoneNumber.toString() }",
                    dob.toString()
                )) { isSuccess ->
                    if (isSuccess) {
                        findNavController().navigate(R.id.action_boardingRegisterFragment_to_dashboardFragment)
                    }
                }
            }
        }
    }
}
