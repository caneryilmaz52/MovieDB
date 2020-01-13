package code.challenge.moviedb.ui.profile

import code.challenge.moviedb.ui.base.BaseContract

class ProfileContract {
    interface View: BaseContract.View {
        fun showErrorMessage(error: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        //any operation
    }
}