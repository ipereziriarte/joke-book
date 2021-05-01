package com.ipereziriarte.data.datasources.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DebugNetworkModule {

    @Provides
    @LoggingInterceptor
    fun provideDebugInterceptor(): Interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @ElementsIntoSet
    fun provideDebugInterceptors(
        @LoggingInterceptor loggingInterceptor: Interceptor,
    ): Set<Interceptor> =
        setOf(loggingInterceptor)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor
