package com.fastorassignment.app.ui.activities.verifyOtp

import `in`.aabhasjindal.otptextview.OTPListener
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fastorassignment.app.ui.activities.home.HomeActivity
import com.fastorassignment.data.enums.RequestResponse
import com.fastorassignment.data.showLongToast
import com.fastorassignment.databinding.ActivityVerifyOtpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyActivity : AppCompatActivity(), OTPListener {
    private lateinit var binding: ActivityVerifyOtpBinding

    private val viewModel by viewModels<VerifyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialSetup()
        setListeners()
        observeChanges()
    }

    private fun initialSetup() {
        val phoneNumber = intent.extras?.get("phone") as String
        viewModel.phoneNumber.value = phoneNumber

        binding.otpSentMessage.text =
            "We have sent the code verification to You Mobile\n Number $phoneNumber"

        // Underline "Request Again" Text
        val requestAgain = SpannableString("Request Again")
        requestAgain.setSpan(UnderlineSpan(), 0, requestAgain.length, 0)
        binding.requestOtpAgain.text = requestAgain
    }

    private fun setListeners() {
        binding.requestOtpAgain.setOnClickListener {
            viewModel.requestOtp()
        }

        binding.otpView.otpListener = this

        binding.proceed.setOnClickListener {
            if (validateFields()) {
                viewModel.loginWithOtp()
            }
        }
    }

    private fun validateFields(): Boolean {
        if (viewModel.otp.value.isNullOrEmpty()) {
            binding.otpView.showError()
            return false
        } else {
            binding.otpView.showSuccess()
            return true
        }
    }

    private fun observeChanges() {
        viewModel.status.observe(this) {
            when (it) {
                RequestResponse.NONE -> {
                }

                RequestResponse.OTP_RESEND_SUCCESS -> {
                    showLongToast("OTP Resend successfully")
                }

                RequestResponse.OTP_RESEND_FAILED -> {
                    showLongToast("OTP Resend Failed")
                }

                RequestResponse.SUCCESS -> {
                    showLongToast("Login successful")

                    val homeIntent = Intent(this, HomeActivity::class.java)
                    homeIntent.putExtra("token", viewModel.data.value?.data?.token)
                    startActivity(homeIntent)
                }

                RequestResponse.ERROR -> {
                    showLongToast("Error sending OTP")
                }

                RequestResponse.NETWORK_ERROR -> {
                    showLongToast("Getting Network error while requesting OTP")
                }

                else -> {}
            }
        }
    }

    override fun onInteractionListener() {
    }

    override fun onOTPComplete(otp: String) {
        viewModel.otp.value = otp
    }
}