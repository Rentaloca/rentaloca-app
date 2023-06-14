package com.rentaloca.rentalocaapp.ui.auth.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentaloca.rentalocaapp.api.ApiConfig
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.response.RegisterResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class SignupViewModel : ViewModel() {
//    fun saveUser(user: UserModel) {
//        viewModelScope.launch {
//            pref.saveUser(user)
//        }
//    }
//}

class SignupViewModel : ViewModel() {
    private val _register = MutableLiveData<RegisterResponse>()
    val register: LiveData<RegisterResponse> = _register

    fun postRegisterData(name: String, email:String, password:String) {
        val client = ApiConfig.getApiService().postRegisterData(name, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    _register.value = response.body()
                } else {
                    Log.e("RegisterViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("RegisterViewModel", "onFailure: ${t.message}")
            }
        })
    }
}