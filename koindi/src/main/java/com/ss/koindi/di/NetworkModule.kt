package com.ss.koindi.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ss.koindi.BuildConfig
import com.ss.koindi.data.remote.UserRestApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory {
        provideGson()
    }

    factory {
        provideBaseUrl()
    }

    single {
        provideLoggingInterceptor()
    }

    single {
        provideHttpClient(httpLoggingInterceptor = get())
    }

    single {
        provideRetrofit(okHttpClient = get(), gson = get(), baseUrl = get())
    }

    single {
        provideUserRestApi(retrofit = get())
    }
}


fun provideGson(): Gson = GsonBuilder().create()

fun provideBaseUrl() = "https://randomuser.me/"

fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
}

fun provideHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val client = OkHttpClient.Builder()

    client.readTimeout(90L, TimeUnit.SECONDS)
    client.writeTimeout(90L, TimeUnit.SECONDS)
    client.connectTimeout(30L, TimeUnit.SECONDS)
    client.addInterceptor(httpLoggingInterceptor)
    client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json").build()
        val request = requestBuilder.method(original.method, original.body).build()
        return@addInterceptor it.proceed(request)
    }

    return client.build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gson: Gson,
    baseUrl: String
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

fun provideUserRestApi(retrofit: Retrofit): UserRestApiService =
    retrofit.create(UserRestApiService::class.java)
