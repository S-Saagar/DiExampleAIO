package com.ss.daggerhiltdi.data.remote

import com.ss.daggerhiltdi.data.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserRestApiService {
    @GET("api/")
    suspend fun getUsers(@Query("results") results: Int): Response<Users>
}