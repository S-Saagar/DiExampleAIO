package com.ss.kodeindi.di

import com.ss.kodeindi.data.remote.UserRemoteDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val remoteDataSourceModule = DI.Module("remoteDataSourceModule") {
    bind() from singleton {
        UserRemoteDataSource(userRestApiService = instance())
    }
}