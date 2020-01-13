package code.challenge.moviedb.models

import com.google.gson.Gson

data class TvList(val topRated: TV,val popular: TV) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}