package com.fastorassignment.app.ui.activities.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.fastorassignment.app.ui.activities.verifyOtp.VerifyActivity
import com.fastorassignment.data.enums.RequestResponse
import com.fastorassignment.data.showLongToast
import com.fastorassignment.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        setContentView(binding.root)

        setListeners()
        observeChanges()
    }

    private fun setListeners() {
        binding.phoneNumberEditText.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrEmpty()) {
                if (binding.phoneNumberInputLayout.error == null) {
                    binding.phoneNumberInputLayout.error = "Please enter your phone number"
                }
            } else {
                if (binding.phoneNumberInputLayout.error != null) {
                    binding.phoneNumberInputLayout.error = null
                }
            }
        }

        binding.requestOtp.setOnClickListener {
            if (validateFields()) {
                viewModel.requestOtp()
            }
        }
    }

    private fun observeChanges() {
        viewModel.status.observe(this) {
            when (it) {
                RequestResponse.NETWORK_ERROR -> {
                    showLongToast("Getting Network error while requesting OTP")
                }

                RequestResponse.SUCCESS -> {
                    showLongToast("OTP Sent")

                    val verifyOtpIntent = Intent(this, VerifyActivity::class.java)
                    verifyOtpIntent.putExtra(
                        "phone",
                        "${viewModel.dialCode}${viewModel.phoneNumber.value}"
                    )
                    startActivity(verifyOtpIntent)
                }

                RequestResponse.ERROR -> {
                    showLongToast("Error sending OTP")
                }
                else -> {}
            }
        }
    }

    private fun validateFields(): Boolean {
        if (viewModel.phoneNumber.value.isNullOrEmpty()) {
            if (binding.phoneNumberInputLayout.error == null) {
                binding.phoneNumberInputLayout.error = "Please enter your phone number"
            }
            return false
        } else {
            if (binding.phoneNumberInputLayout.error != null) {
                binding.phoneNumberInputLayout.error = null
            }
            return true
        }
    }
}