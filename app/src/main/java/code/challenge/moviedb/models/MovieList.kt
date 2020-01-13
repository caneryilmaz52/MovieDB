package code.challenge.moviedb.models

import com.google.gson.Gson

data class MovieList(val topRated: Movie, val nowPlaying: Movie, val popular: Movie) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}