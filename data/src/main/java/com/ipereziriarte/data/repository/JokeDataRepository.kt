package com.ipereziriarte.data

import com.ipereziriarte.data.datasources.remote.jokes.CallResult
import com.ipereziriarte.data.datasources.remote.jokes.JokesRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeDataRepository @Inject constructor(private val dataSource: JokesRemoteDataSource) {

    suspend fun fetchGeneralJokes(): CallResult = dataSource.getTenJokes("general")

    suspend fun fetchKnockJokes(): CallResult = dataSource.getTenJokes("knock-knock")

    suspend fun fetchProgramingJokes(): CallResult = dataSource.getTenJokes("programming")
}
