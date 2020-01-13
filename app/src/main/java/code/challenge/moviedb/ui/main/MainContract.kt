package code.challenge.moviedb.ui.main

import code.challenge.moviedb.ui.base.BaseContract

class MainContract {
    interface View: BaseContract.View{
        fun showBottomNavigation()
        fun showMovieFragment()
        fun showTVFragment()
        fun showProfileFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun onBottomNavigationItemClick(view:View,index:Int)
    }
}