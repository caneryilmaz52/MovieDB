package code.challenge.moviedb.models

import com.google.gson.Gson

data class MovieDetailList(val movieDetail: MovieDetail, val credits: Credits) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}