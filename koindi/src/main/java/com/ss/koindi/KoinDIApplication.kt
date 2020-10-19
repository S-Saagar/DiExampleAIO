package com.ss.koindi

import android.app.Application
import com.ss.koindi.di.networkModule
import com.ss.koindi.di.remoteDataSourceModule
import com.ss.koindi.di.repositoryModule
import com.ss.koindi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinDIApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@KoinDIApplication)

            modules(
                listOf(
                    networkModule,
                    remoteDataSourceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}