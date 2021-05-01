package com.ipereziriarte.data.datasources.remote.di

import com.ipereziriarte.data.datasources.remote.jokes.JokesApi
import com.ipereziriarte.data.datasources.remote.jokes.JokesApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule() {

    @Binds
    abstract fun bindJokesApi(jokesApiImpl: JokesApiImpl): JokesApi
}
