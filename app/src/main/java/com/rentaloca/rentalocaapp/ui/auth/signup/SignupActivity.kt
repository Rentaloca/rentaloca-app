package com.rentaloca.rentalocaapp.ui.auth.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.rentaloca.rentalocaapp.databinding.ActivitySignupBinding
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.ui.ViewModelFactory
import com.rentaloca.rentalocaapp.ui.auth.signin.SigninActivity

//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var factory: ViewModelFactory
//    private lateinit var signupViewModel: SignupViewModel
    private val signupViewModel: SignupViewModel by viewModels { factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        factory = ViewModelFactory.getInstance(this)
        setContentView(binding.root)

//        setupViewModel()
        setupAction()
    }

//    private fun setupViewModel() {
//        signupViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(UserPreference.getInstance(dataStore))
//        )[SignupViewModel::class.java]
//    }

    private fun setupAction() {
        binding.buttonSignup.setOnClickListener {
            val fullname = binding.fullnameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val notelpon = binding.notelponEditText.text.toString()
            val alamat = binding.alamatEditText.text.toString()
            when {
                fullname.isEmpty() -> {
                    binding.fullnameEditText.error = "Masukkan email"
                }
                email.isEmpty() -> {
                    binding.emailEditText.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.passwordEditText.error = "Masukkan password"
                }
                else -> {
//                    signupViewModel.saveUser(UserModel(fullname, email, password, notelpon, alamat, false))
//                    AlertDialog.Builder(this).apply {
//                        setTitle("Registration Success!")
//                        setMessage("Congratulations, your account has been succesfully created.")
//                        setPositiveButton("Continue") { _, _ ->
//                            finish()
//                        }
//                        create()
//                        show()
//                    }

                    signupViewModel.postRegisterData(
                        binding.fullnameEditText.text.toString(),
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString()
                    )
                    signupViewModel.register.observe(this@SignupActivity) {
                            response -> if (!response.error) {
                        startActivity(Intent(this@SignupActivity, SigninActivity::class.java))
                    }
                    }
                }
            }
        }
    }
}