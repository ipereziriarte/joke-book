package com.ipereziriarte.jokebook.ui.jokelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ipereziriarte.data.JokeDataRepository
import com.ipereziriarte.data.datasources.remote.jokes.CallResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class JokeListGeneralViewModel @Inject constructor(private val jokeDataRepository: JokeDataRepository) : ViewModel() {

    val fetchJokes = liveData(Dispatchers.IO) {
        emit(CallResult.Loading)
        try {
            emit(jokeDataRepository.fetchGeneralJokes())
        } catch (e: Exception) {
            emit(CallResult.Failure(e))
        }
    }
}
