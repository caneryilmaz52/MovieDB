package code.challenge.moviedb.ui.movie

import code.challenge.moviedb.models.MovieList
import code.challenge.moviedb.ui.base.BaseContract

class MovieContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: MovieList)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}