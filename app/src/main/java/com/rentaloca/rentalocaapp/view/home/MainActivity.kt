package com.rentaloca.rentalocaapp.view.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.rentaloca.rentalocaapp.R
import com.rentaloca.rentalocaapp.databinding.ActivityMainBinding
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.view.ViewModelFactory
import com.rentaloca.rentalocaapp.view.auth.signin.SigninActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]

        mainViewModel.getUser().observe(this) { user ->
            binding.nameTextView.text = getString(R.string.greeting, user.fullname)
        }
    }

    private fun setupAction() {
        binding.logoutButton.setOnClickListener {
            mainViewModel.logout()
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }
    }
}