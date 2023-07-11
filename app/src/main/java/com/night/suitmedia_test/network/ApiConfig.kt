package com.night.suitmedia_test.network

import com.night.suitmedia_test.BuildConfig
import com.night.suitmedia_test.utils.Constants.API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun apiService(): ApiService {
        val loggingInterceptor =
            if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
            else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}