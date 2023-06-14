package com.rentaloca.rentalocaapp.ui.auth.signin

import android.util.Log
import androidx.lifecycle.*
import com.rentaloca.rentalocaapp.api.ApiConfig
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.repository.UserRepository
import com.rentaloca.rentalocaapp.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class SigninViewModel(private val pref: UserPreference) : ViewModel() {
//    fun getUser(): LiveData<UserModel> {
//        return pref.getUser().asLiveData()
//    }
//
//    fun login() {
//        viewModelScope.launch {
//            pref.login()
//        }
//    }
//}

class SigninViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _login = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse> = _login

    fun postLoginData(email:String, password:String) {
        val client = ApiConfig.getApiService().postLoginData(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    _login.value = response.body()
                } else {
                    Log.e("LoginViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun postUser(session: UserModel) {
        viewModelScope.launch {
            userRepository.postUser(session)
        }
    }

    fun userLogin() {
        viewModelScope.launch {
            userRepository.userLogin()
        }
    }
}