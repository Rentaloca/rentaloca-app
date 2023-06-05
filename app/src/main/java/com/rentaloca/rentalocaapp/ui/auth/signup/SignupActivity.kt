package com.rentaloca.rentalocaapp.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rentaloca.rentalocaapp.databinding.ActivitySignupBinding
import com.rentaloca.rentalocaapp.ui.auth.signin.SigninActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.buttonToSignin.setOnClickListener{
            startActivity(Intent(this@SignupActivity, SigninActivity::class.java))
        }
    }
}