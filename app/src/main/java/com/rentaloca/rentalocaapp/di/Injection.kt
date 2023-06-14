package com.rentaloca.rentalocaapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.rentaloca.rentalocaapp.model.UserPreference
import com.rentaloca.rentalocaapp.repository.UserRepository

val Context.database: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val preference = UserPreference.getInstance(context.database)
        return UserRepository.getInstance(preference)
    }
}
