package com.ipereziriarte.data.datasources.remote.jokes

import com.ipereziriarte.data.repository.Joke
import javax.inject.Inject

class JokesRemoteDataSource @Inject constructor(private val jokesApi: JokesApi) {

    suspend fun getTenJokes(category: String): CallResult {
        try {
            val result = jokesApi.getTenJokes(category)
            if (result.isSuccessful) {
                val jokes = mutableListOf<Joke>()
                if (result.body().isNullOrEmpty()) {
                    throw IllegalStateException("Empty result response")
                } else {
                    result.body()!!.map {
                        val joke = it.toJoke()
                        jokes.add(joke)
                    }
                    return CallResult.Success(jokes)
                }
            } else {
                throw IllegalStateException("${result.errorBody()}")
            }
        } catch (exception: Exception) {
            return CallResult.Failure(exception)
        }
    }
}
