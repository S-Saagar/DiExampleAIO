package com.ss.kodeindi.data.remote

import com.ss.kodeindi.data.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserRestApiService {
    @GET("api/")
    suspend fun getUsers(@Query("results") results: Int): Response<Users>
}