package code.challenge.moviedb.ui.detail

import code.challenge.moviedb.models.MovieDetailList
import code.challenge.moviedb.models.TvDetailList
import code.challenge.moviedb.ui.base.BaseContract

class DetailContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(movieList:MovieDetailList?,  tvList:TvDetailList?)
        fun backPress()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(itemID:Int, isMovie:Boolean)
        fun backPress()
    }
}