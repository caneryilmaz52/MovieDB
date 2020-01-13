package code.challenge.moviedb.ui.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter :MainContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private lateinit var view:MainContract.View

    override fun onBottomNavigationItemClick(view:MainContract.View, index:Int) {
        this.view = view
        when(index){
            0 -> {
                view.showMovieFragment()
            }
            1 -> {
                view.showTVFragment()
            }
            2 -> {
                view.showProfileFragment()
            }
        }
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view=view
        view.showBottomNavigation()
        view.showMovieFragment()
    }

}