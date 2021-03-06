package com.relieve.android.screen.fragment.boarding


import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.relieve.android.R
import com.relieve.android.helper.*
import com.relieve.android.rsux.component.SnackBarItem
import com.relieve.android.rsux.framework.RsuxFragment
import com.relieve.android.rsux.framework.RsuxObserver
import com.relieve.android.screen.viewmodel.BoardingViewModel
import kotlinx.android.synthetic.main.fragment_boarding_home.*

class BoardingHomeFragment : RsuxFragment<BoardingViewModel.BoardingState, BoardingViewModel>() {
    companion object {
        const val RC_SIGN_IN = 101
    }

    init {
        layoutId = R.layout.fragment_boarding_home
    }

    // Configure Google Sign In
    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    override val vModel by lazy {  ViewModelProviders.of(this).get(BoardingViewModel::class.java) }

    override fun render(state: BoardingViewModel.BoardingState) {
        if (preferencesHelper?.isSignedIn == true)
            findNavController().navigate(R.id.action_boardingHomeFragment_to_dashboardFragment)

        tvSignNow.setOnClickListener {
            findNavController().navigate(R.id.action_boardingHomeFragment_to_boardingLoginFragment)
        }

        tvSignGoogle.setOnClickListener {
            activity?.let { act ->
                val client = GoogleSignIn.getClient(act, gso)
                startActivityForResult(client.signInIntent, RC_SIGN_IN)
            }
        }

        tvRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_boardingHomeFragment_to_boardingRegisterFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                if (!account?.idToken.isNullOrEmpty() && !account?.displayName.isNullOrEmpty()) {
                    vModel.onGoogleLogin(account?.idToken.toString(), account?.displayName.toString()) { isSuccess, resToken ->

                        if (isSuccess) {
                            preferencesHelper?.apply {
                                isSignedIn = true
                                token = resToken?.token
                                tokenRefresh = resToken?.refreshToken
                                tokenExpire = resToken?.expiresIn ?: 0
                            }

                            findNavController().navigate(R.id.action_boardingHomeFragment_to_dashboardFragment)
                        } else {
                            SnackBarItem.make(rootBoardingHome, Snackbar.LENGTH_LONG).apply {
                                setMessage(getString(R.string.unknown_error))
                                setButtonText(getString(R.string.ok))
                                setButtonClick { dismiss() }
                                show()
                            }
                        }
                    }
                } else {
                    SnackBarItem.make(rootBoardingHome, Snackbar.LENGTH_LONG).apply {
                        setMessage(getString(R.string.unknown_error))
                        setButtonText(getString(R.string.ok))
                        setButtonClick { dismiss() }
                        show()
                    }
                }
            } catch (e: ApiException) {
                SnackBarItem.make(rootBoardingHome, Snackbar.LENGTH_LONG).apply {
                    setMessage(getString(R.string.unknown_error))
                    setButtonText(getString(R.string.ok))
                    setButtonClick { dismiss() }
                    show()
                }
            }
        }
    }
}
