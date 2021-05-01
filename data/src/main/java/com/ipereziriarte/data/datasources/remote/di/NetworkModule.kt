package com.ipereziriarte.data.datasources.remote.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @BaseUrl
    @Provides
    internal fun provideBaseUrl(): String = "https://official-joke-api.appspot.com"

    @Provides
    @ElementsIntoSet
    fun provideDefaultInterceptors(): Set<Interceptor> = emptySet()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        interceptors: @JvmSuppressWildcards Set<Interceptor>
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        interceptors.forEach {
            client.addInterceptor(it)
        }
        client.connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)

        return client.build()
    }

    @Provides
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(@BaseUrl baseUrl: String, client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(MoshiConverterFactory.create(moshi)).client(client)
            .build()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl
