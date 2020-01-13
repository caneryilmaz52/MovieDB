package code.challenge.moviedb.ui.splash

import code.challenge.moviedb.ui.base.BaseContract

class SplashContract {
    interface View: BaseContract.View{
        fun finishSplash()
    }

    interface Presenter: BaseContract.Presenter<SplashContract.View> {
        //some actions
    }
}
