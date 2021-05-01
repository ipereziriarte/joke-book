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

    fun getGeneralJokes(): List<Joke> {
        return listOf(
            Joke(225, "general", "What do you call a pig with three eyes", "Piig"),
            Joke(258, "general", "what is the difference between ignorance and apathy?", "I don't know and I don't care"),
            Joke(80, "general", "A weasel walks into a bar. The bartender says, \"Wow, I've never served a weasel before. What can I get for you?\"", "Pop,goes the weasel.")

        )
    }

    fun getKnockJokes(): List<Joke> {
        return listOf(
            Joke(14, "knock-knock", "Knock knock. Who's there? Little old lady. Little old lady who?", "I didn't know you could yodel!"),
            Joke(12, "knock-knock", "Knock knock. Who's there? A broken pencil. A broken pencil who?", "Never mind. It's pointless."),
            Joke(34, "knock-knock", "Knock knock. Who's there? Opportunity.", "That is impossible. Opportunity doesn’t come knocking twice!")
        )
    }

    fun getProgrammingJokes(): List<Joke> {
        return listOf(
            Joke(23, "programming", "Why do programmers always mix up Halloween and Christmas?", "Because Oct 31 == Dec 25"),
            Joke(56, "programming", "How do you check if a webpage is HTML5?", "Try it out on Internet Explorer")
        )
    }
}

data class Joke(val id: Long, val type: String, val setup: String, val punchLine: String)
