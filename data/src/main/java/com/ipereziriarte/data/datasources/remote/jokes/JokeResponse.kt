package com.ipereziriarte.data.datasources.remote.jokes

import com.squareup.moshi.JsonClass

/**
 * {
 * "id": 23,
 * "type": "programming",
 * "setup": "Why do programmers always mix up Halloween and Christmas?",
 * "punchline": "Because Oct 31 == Dec 25"
 * }
 */
@JsonClass(generateAdapter = true)
data class JokeResponse(val id: String, val type: String, val setup: String, val punchline: String)
