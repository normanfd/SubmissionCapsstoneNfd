package com.norman.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.norman.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(context, true, RetentionManager.Period.ONE_HOUR)
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250000L)
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/f78NVAesYtdZ9OGSbK7VtGQkSIVykh3DnduuLIJHMu4=")
            .add(hostname, "sha256/G9LNNAql897egYsabashkzUCTEJkWBzgoEtk8X/678c=")
            .build()
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}