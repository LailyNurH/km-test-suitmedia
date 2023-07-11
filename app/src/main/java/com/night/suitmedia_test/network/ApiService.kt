package com.night.suitmedia_test.network

import com.night.suitmedia_test.network.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): UserResponse
}