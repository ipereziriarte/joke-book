package com.ipereziriarte.jokebook.ui.jokelist

import androidx.lifecycle.ViewModel
import com.ipereziriarte.data.Joke
import com.ipereziriarte.data.JokeDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JokeListKnockViewModel @Inject constructor(private val jokeDataRepository: JokeDataRepository) : ViewModel() {

    var knockJokes: List<Joke> = emptyList()

    init {
        knockJokes = jokeDataRepository.getKnockJokes()
    }
}
