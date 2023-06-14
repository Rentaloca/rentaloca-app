package com.rentaloca.rentalocaapp.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[NAME_KEY] ?:"",
                preferences[TOKEN_KEY] ?: "",
                preferences[STATE_KEY] ?: false,
//                preferences[EMAIL_KEY] ?: "",
//                preferences[PASSWORD_KEY] ?: "",
//                preferences[NOTELPON_KEY] ?: "",
//                preferences[ALAMAT_KEY] ?: "",
                )
        }
    }

    suspend fun postUser(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = user.fullname
            preferences[TOKEN_KEY] = user.token
            preferences[STATE_KEY] = user.isLogin
//            preferences[EMAIL_KEY] = user.email
//            preferences[PASSWORD_KEY] = user.password
//            preferences[NOTELPON_KEY] = user.notelpon
//            preferences[ALAMAT_KEY] = user.alamat
        }
    }

    suspend fun login() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val STATE_KEY = booleanPreferencesKey("state")
//        private val EMAIL_KEY = stringPreferencesKey("email")
//        private val PASSWORD_KEY = stringPreferencesKey("password")
//        private val NOTELPON_KEY = stringPreferencesKey("notelpon")
//        private val ALAMAT_KEY = stringPreferencesKey("alamat")


        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}