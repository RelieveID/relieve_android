package com.relieve.android.fragment.boarding


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.relieve.android.R
import com.relieve.android.helper.PreferencesHelper
import com.relieve.android.rsux.component.SnackBarItem
import com.relieve.android.viewmodel.boarding.BoardingViewModel
import kotlinx.android.synthetic.main.fragment_boarding_home.*

class BoardingHomeFragment : Fragment() {
    companion object {
        const val RC_SIGN_IN = 101
    }

    // Configure Google Sign In
    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    private val preferencesHelper by lazy {
        context?.run { PreferencesHelper(this) }
    }

    private val vModel by lazy {
        ViewModelProviders.of(this).get(BoardingViewModel::class.java)
    }

    override fun onDestroy() {
        vModel.onDestroy()
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boarding_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (preferencesHelper?.isSignedIn == true)
            findNavController().navigate(R.id.action_boardingHomeFragment_to_dashboardFragment)
        else render()
    }

    private fun render() {
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
