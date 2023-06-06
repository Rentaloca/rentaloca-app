package com.rentaloca.rentalocaapp.view.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference
import kotlinx.coroutines.launch

class SigninViewModel(private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }
}