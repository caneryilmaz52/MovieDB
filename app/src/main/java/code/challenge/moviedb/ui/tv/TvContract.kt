package code.challenge.moviedb.ui.tv

import code.challenge.moviedb.models.TvList
import code.challenge.moviedb.ui.base.BaseContract


class  TvContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: TvList)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}