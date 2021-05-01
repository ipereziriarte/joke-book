package com.ipereziriarte.data.datasources.remote.jokes

import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class JokesApiImpl @Inject constructor(private val retrofit: Retrofit) : JokesApi {

    override suspend fun getTenJokes(category: String): Response<List<JokeResponse>> =
        retrofit.create(JokesService::class.java).getTenJokes(category)
}
