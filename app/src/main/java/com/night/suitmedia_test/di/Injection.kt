package com.night.suitmedia_test.di

import android.content.Context
import com.night.suitmedia_test.network.ApiConfig
import com.night.suitmedia_test.network.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.apiService()
        return UserRepository(apiService)
    }
}