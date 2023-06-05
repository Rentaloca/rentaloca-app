package com.rentaloca.rentalocaapp.ui.auth.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rentaloca.rentalocaapp.databinding.ActivitySigninBinding
import com.rentaloca.rentalocaapp.ui.auth.signup.SignupActivity

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.buttonSignup.setOnClickListener{
            startActivity(Intent(this@SigninActivity, SignupActivity::class.java))
        }
    }
}