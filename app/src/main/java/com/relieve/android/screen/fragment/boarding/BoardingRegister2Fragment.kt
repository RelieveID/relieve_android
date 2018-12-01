package com.relieve.android.screen.fragment.boarding


import android.app.DatePickerDialog
import android.text.method.LinkMovementMethod
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.relieve.android.R
import com.relieve.android.helper.*
import com.relieve.android.network.data.relieve.UserData
import com.relieve.android.rsux.base.EditTextChangeListener
import com.relieve.android.rsux.component.SnackBarItem
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.screen.viewmodel.BoardingViewModel
import kotlinx.android.synthetic.main.fragment_boarding_register_2.*
import java.util.*

class BoardingRegister2Fragment : RsuxFragment<BoardingViewModel.BoardingState, BoardingViewModel>() {
    override val vModel by lazy {  ViewModelProviders.of(this).get(BoardingViewModel::class.java) }

    private val textInputs by lazy {
        listOf (
            inputFullName,
            inputDateOfBirth,
            inputPhoneNumberArea,
            inputPhoneNumber
        )
    }

    init {
        layoutId = R.layout.fragment_boarding_register_2
    }

    private fun enableTextClick() {
        tvTermsNCondition.linksClickable = true
        tvTermsNCondition.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun render(state: BoardingViewModel.BoardingState) {
        enableTextClick()

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

        inputDateOfBirth.editText?.setOnClickListener {
            val c = Calendar.getInstance()
            val currentYear = c.get(Calendar.YEAR)
            val currentMonth = c.get(Calendar.MONTH)
            val currentDay = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(inputDateOfBirth.context, { _, year, month, day ->
                inputDateOfBirth.editText?.setText(getString(R.string.bod_format, year, month+1, day))
            }, currentYear, currentMonth, currentDay).show()
        }


        tvRegisterSubmit.setOnClickListener {
            val fullName = inputFullName.editText?.text
            val dob = inputDateOfBirth.editText?.text
            val phoneArea = inputPhoneNumberArea.editText?.text
            val phoneNumber = inputPhoneNumber.editText?.text

            val texts = listOf(fullName, dob, phoneArea, phoneNumber)

            var allIsValid = true
            texts.forEachIndexed { index, editable ->
                if (editable.isNullOrEmpty()) {
                    textInputs[index].error = getString(R.string.please_fill)
                    allIsValid = false
                }
            }

            if (allIsValid) {
//                vModel.registerClick(UserData(
//                    username.toString(),
//                    password.toString(),
//                    fullName.toString(),
//                    email.toString(),
//                    "${ phoneArea.toString() } ${ phoneNumber.toString() }",
//                    dob.toString()
//                )) { isSuccess, resToken ->
//                    if (isSuccess) {
//                        preferencesHelper?.apply {
//                            isSignedIn = true
//                            token = resToken?.token
//                            tokenRefresh = resToken?.refreshToken
//                            tokenExpire = resToken?.expiresIn ?: 0
//                        }
//
//                        findNavController().navigate(R.id.action_boardingRegisterFragment_to_dashboardFragment)
//                    } else {
//                        SnackBarItem.make(rootBoardingRegister, Snackbar.LENGTH_LONG).apply {
//                            setMessage(getString(R.string.unknown_error))
//                            setButtonText(getString(R.string.ok))
//                            setButtonClick { dismiss() }
//                            show()
//                        }
//                    }
//                }
            }
        }
    }
}
