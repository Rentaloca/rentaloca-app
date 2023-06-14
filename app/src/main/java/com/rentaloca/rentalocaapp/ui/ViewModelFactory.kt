package com.rentaloca.rentalocaapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rentaloca.rentalocaapp.di.Injection
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.repository.UserRepository
import com.rentaloca.rentalocaapp.ui.auth.signin.SigninViewModel
import com.rentaloca.rentalocaapp.ui.auth.signup.SignupViewModel
import com.rentaloca.rentalocaapp.ui.detail.DetailViewModel
import com.rentaloca.rentalocaapp.ui.profile.ProfileViewModel

class ViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
//                ProfileViewModel() as T
//            }
//            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
//                DetailViewModel() as T
//            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel() as T
            }
            modelClass.isAssignableFrom(SigninViewModel::class.java) -> {
                SigninViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }
}