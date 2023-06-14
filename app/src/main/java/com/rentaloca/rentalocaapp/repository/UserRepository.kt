package com.rentaloca.rentalocaapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.rentaloca.rentalocaapp.model.UserModel
import com.rentaloca.rentalocaapp.model.UserPreference

class UserRepository private constructor(
    private val pref: UserPreference,
) {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    suspend fun postUser(session: UserModel) {
        pref.postUser(session)
    }

    suspend fun userLogin() {
        pref.login()
    }

    suspend fun userLogout() {
        pref.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(preference: UserPreference): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(preference)
            }.also { instance = it }
    }
}