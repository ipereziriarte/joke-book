package com.ipereziriarte.data.datasources.remote.jokes

import retrofit2.Response

interface JokesApi {

    suspend fun getTenJokes(category: String): Response<List<JokeResponse>>
}
