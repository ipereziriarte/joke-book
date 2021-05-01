package com.ipereziriarte.jokebook.ui.jokelist

import androidx.lifecycle.ViewModel
import com.ipereziriarte.data.Joke
import com.ipereziriarte.data.JokeDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JokeListGeneralViewModel @Inject constructor(private val jokeDataRepository: JokeDataRepository) : ViewModel() {

    var generalJokes: List<Joke> = emptyList()

    init {
        generalJokes = jokeDataRepository.getGeneralJokes()
    }
}
