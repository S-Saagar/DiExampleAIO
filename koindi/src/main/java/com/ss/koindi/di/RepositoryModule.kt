package com.ss.koindi.di

import com.ss.koindi.data.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        UsersRepository(remoteRemoteDataSource = get())
    }
}