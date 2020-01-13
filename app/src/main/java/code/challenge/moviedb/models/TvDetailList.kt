package code.challenge.moviedb.models

import com.google.gson.Gson

data class TvDetailList(val tvDetail: TVDetail, val credits: Credits) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}