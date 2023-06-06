package com.rentaloca.rentalocaapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.view.auth.signin.SigninViewModel
import com.rentaloca.rentalocaapp.view.auth.signup.SignupViewModel
import com.rentaloca.rentalocaapp.view.home.MainViewModel
import com.rentaloca.rentalocaapp.view.splashscreen.SplashScreenViewModel

class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> {
                SplashScreenViewModel(pref) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SigninViewModel::class.java) -> {
                SigninViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}