package com.ss.koindi.di

import com.ss.koindi.data.remote.UserRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single {
        UserRemoteDataSource(userRestApiService = get())
    }
}