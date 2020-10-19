package com.ss.kodeindi.di

import com.ss.kodeindi.data.repository.UsersRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider


val repositoryModule = DI.Module("repositoryModule") {
    bind() from provider {
        UsersRepository(remoteRemoteDataSource = instance())
    }
}