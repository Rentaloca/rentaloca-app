package com.rentaloca.rentalocaapp.ui.auth.signin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.rentaloca.rentalocaapp.databinding.ActivitySigninBinding
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.ui.MainActivity
import com.rentaloca.rentalocaapp.ui.ViewModelFactory
import com.rentaloca.rentalocaapp.ui.auth.signup.SignupActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SigninActivity : AppCompatActivity() {
    private lateinit var signinViewModel: SigninViewModel
    private lateinit var binding: ActivitySigninBinding
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        signinViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SigninViewModel::class.java]

        signinViewModel.getUser().observe(this) { user ->
            this.user = user
        }
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                email.isEmpty() -> {
                    binding.emailEditText.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.passwordEditText.error = "Masukkan password"
                }
                email != user.email -> {
                    binding.emailEditText.error = "Email tidak sesuai"
                }
                password != user.password -> {
                    binding.passwordEditText.error = "Password tidak sesuai"
                }
                else -> {
                    signinViewModel.login()
                    AlertDialog.Builder(this).apply {
                        setTitle("Login Success!")
                        setMessage("You have successfully logged in. Happy choosing rental outfits!")
                        setPositiveButton("Continue") { _, _ ->
                            val intent = Intent(context, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }
                }
            }
        }

        binding.buttonSignup.setOnClickListener{
            startActivity(Intent(this@SigninActivity, SignupActivity::class.java))
        }
    }
}