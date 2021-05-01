package com.ipereziriarte.data.datasources.remote.jokes

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface JokesService {

    @Headers("Accept: application/json")
    @GET("/jokes/{categoryId}/ten")
    suspend fun getTenJokes(@Path("categoryId") categoryId: String): Response<List<JokeResponse>>
}
