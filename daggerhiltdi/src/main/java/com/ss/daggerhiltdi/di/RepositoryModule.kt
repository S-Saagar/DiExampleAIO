package com.ss.daggerhiltdi.di

import com.ss.daggerhiltdi.data.remote.UserRemoteDataSource
import com.ss.daggerhiltdi.data.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(remoteRemoteDataSource: UserRemoteDataSource) =
        UsersRepository(remoteRemoteDataSource)
}