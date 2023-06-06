package com.rentaloca.rentalocaapp.view.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference

class SplashScreenViewModel(private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}