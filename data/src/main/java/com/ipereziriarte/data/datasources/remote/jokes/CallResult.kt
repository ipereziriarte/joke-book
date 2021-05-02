package com.ipereziriarte.data.datasources.remote.jokes

import com.ipereziriarte.data.repository.Joke

sealed class CallResult {

    object Loading : CallResult()
    data class Success(val data: List<Joke>) : CallResult()
    data class Failure(val exception: Exception) : CallResult()
}
