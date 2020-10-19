package com.ss.daggerhiltdi.di

import com.ss.daggerhiltdi.data.remote.UserRemoteDataSource
import com.ss.daggerhiltdi.data.remote.UserRestApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideUserDataSource(userRestApiService: UserRestApiService) = UserRemoteDataSource(userRestApiService)
}