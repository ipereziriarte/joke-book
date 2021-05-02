package com.ipereziriarte.data.datasources.remote.jokes

import org.junit.Assert.assertEquals
import org.junit.Test

class JokeResponseTest {

    @Test
    fun joke_mapping_is_correct() {
        val jokeResponse = JokeResponse(32, "programming", "How many apples grow on a tree", "All of them!")

        val joke = jokeResponse.toJoke()

        assertEquals("How many apples grow on a tree", joke.setup)
        assertEquals("All of them!", joke.punchLine)
    }
}
